package com.example.springbootpagination.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortPage<T> {

    private List<T> content;
    private int currentPage;
    private long totalItems;
    private int totalPages;

}
