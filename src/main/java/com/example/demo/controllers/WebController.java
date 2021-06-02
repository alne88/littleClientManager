package com.example.demo.controllers;

import com.example.demo.models.ClientDto;
import com.example.demo.models.LookupValueDto;
import com.example.demo.models.LookupValueDto.LookupValueCode;
import com.example.demo.services.ClientService;
import com.example.demo.services.LookupValuesService;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class WebController {

    private final ClientService clientService;
    private final UserService userService;
    private final LookupValuesService lookupValuesService;

    public WebController(ClientService clientService, UserService userService, LookupValuesService lookupValuesService) {
        this.clientService = clientService;
        this.userService = userService;
        this.lookupValuesService = lookupValuesService;
    }

    @GetMapping("/")
    public String clientsPage(Model model) {
        List<ClientDto> clients = clientService.findUserClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/client/add")
    public String createNewClient(Model model) {
        model.addAttribute("client", new ClientDto());
        model.addAttribute("countries", getCountryValues());
        return "client/add";
    }

    @PostMapping("/client/add")
    public String insertNewClient(@ModelAttribute ClientDto clientDto) {
        clientDto.setCreatedByUserId(userService.getLoggedInUserId());
        clientService.insert(clientDto);
        return "redirect:/";
    }

    @GetMapping("/client/{clientId}/edit")
    public String clientEditForm(@PathVariable Long clientId, Model model) {
        model.addAttribute("client", clientService.findById(clientId));
        model.addAttribute("countries", getCountryValues());
        return "client/edit";
    }

    @GetMapping("/client/{clientId}/delete")
    public String clientDelete(@PathVariable Long clientId) {
        clientService.delete(clientId);
        return "redirect:/";
    }

    @PostMapping("/client/update")
    public String clientUpdate(@ModelAttribute ClientDto clientDto) {
        clientDto.setCreatedByUserId(userService.getLoggedInUserId());
        clientService.update(clientDto);
        return "redirect:/";
    }

    private List<String> getCountryValues() {
        return lookupValuesService.findByCode(LookupValueCode.COUNTRY.name()).stream()
            .map(LookupValueDto::getValue)
            .collect(toList());
    }
}
