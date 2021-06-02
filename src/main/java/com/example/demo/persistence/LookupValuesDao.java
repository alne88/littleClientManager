package com.example.demo.persistence;

import com.example.demo.models.LookupValueDto;

import java.util.List;

public interface LookupValuesDao {

    List<LookupValueDto> findByCode(String code);

}
