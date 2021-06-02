package com.example.demo.services;

import com.example.demo.models.LookupValueDto;
import com.example.demo.persistence.LookupValuesDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookupValuesService {

    private final LookupValuesDao lookupValuesDao;

    public LookupValuesService(LookupValuesDao lookupValuesDao) {
        this.lookupValuesDao = lookupValuesDao;
    }

    public List<LookupValueDto> findByCode(String code) {
        return lookupValuesDao.findByCode(code);
    }

}
