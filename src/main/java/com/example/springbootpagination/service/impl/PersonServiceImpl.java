package com.example.springbootpagination.service.impl;

import com.example.springbootpagination.dto.ShortPage;
import com.example.springbootpagination.model.Person;
import com.example.springbootpagination.repository.PersonRepository;
import com.example.springbootpagination.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public Page<Person> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        var sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        var pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return personRepository.findAll(pageable);
    }

    @Override
    public Slice<Person> findAllSlice(int pageNo, int pageSize, String sortBy, String sortDirection) {
        var sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        var pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return personRepository.findAllSlice(pageable);
    }

    @Override
    public ShortPage<Person> findAllShort(int pageNo, int pageSize, String sortBy, String sortDirection) {
        var sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        var pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        var all = personRepository.findAll(pageable);
        return new ShortPage<>(all.getContent(), all.getNumber(), all.getTotalElements(), all.getTotalPages());
    }

    @Override
    public Page<Person> findAll(int pageNo, int pageSize, String[] sort) {
        var orders = new ArrayList<Order>();

        if (sort[0].contains(",")) {
            // will sort more than 2 columns
            for (String sortOrder : sort) {
                // sortOrder="column, direction"
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[column, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }

        var sortBy = Sort.by(orders);
        var pageable = PageRequest.of(pageNo - 1, pageSize, sortBy);
        return personRepository.findAll(pageable);
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equalsIgnoreCase("ASC")) {
            return Sort.Direction.ASC;
        } else if (direction.equalsIgnoreCase("DESC")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
}
