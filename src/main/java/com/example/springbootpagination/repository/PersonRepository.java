package com.example.springbootpagination.repository;

import com.example.springbootpagination.model.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Slice<Person> findByAgeAfter(int age, Pageable pageable);

    default Slice<Person> findAllSlice(Pageable pageable) {
        return findByAgeAfter(0, pageable);
    }
}
