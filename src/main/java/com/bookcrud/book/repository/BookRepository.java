package com.bookcrud.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookcrud.book.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{
    
}
