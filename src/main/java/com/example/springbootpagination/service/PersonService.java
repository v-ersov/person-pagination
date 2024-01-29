package com.example.springbootpagination.service;

import com.example.springbootpagination.dto.ShortPage;
import com.example.springbootpagination.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface PersonService {

    Page<Person> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

    Slice<Person> findAllSlice(int pageNo, int pageSize, String sortBy, String sortDirection);

    ShortPage<Person> findAllShort(int pageNo, int pageSize, String sortBy, String sortDirection);

    Page<Person> findAll(int pageNo, int pageSize, String[] sort);


}
