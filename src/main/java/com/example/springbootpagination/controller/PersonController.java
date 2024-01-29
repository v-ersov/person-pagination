package com.example.springbootpagination.controller;

import com.example.springbootpagination.dto.ShortPage;
import com.example.springbootpagination.model.Person;
import com.example.springbootpagination.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping()
    public ResponseEntity<Page<Person>> findAll(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(personService.findAll(pageNo, pageSize, sortBy, sortDirection));
    }

    @GetMapping("/slice")
    public ResponseEntity<Slice<Person>> findAllSlice(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(personService.findAllSlice(pageNo, pageSize, sortBy, sortDirection));
    }

    @GetMapping("/short")
    public ResponseEntity<ShortPage<Person>> findAllShort(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(personService.findAllShort(pageNo, pageSize, sortBy, sortDirection));
    }

    @GetMapping("/multiple_sort")
    public ResponseEntity<Page<Person>> findAllMultipleSort(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "sort") String[] sort) {
        return ResponseEntity.ok(personService.findAll(pageNo, pageSize, sort));
    }

}
