package com.bookcrud.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookcrud.book.entity.Book;
import com.bookcrud.book.repository.BookRepository;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
public class BookController {

    @Autowired
    BookRepository repository;


    // Hi message for testing api - http://localhost:8080/bookapi/hi
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/hi" , method = RequestMethod.GET)
    public String sayHi(){
        return "Hi";
    }

    //Create Books - http://localhost:8080/bookapi/create
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/create" , method=RequestMethod.POST)
    public Book createBook(@RequestBody Book book){
        return repository.save(book);
    }

    //Find all books - http://localhost:8080/bookapi/findall
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/findall" , method = RequestMethod.GET)
    public List<Book> findAll(){
        return repository.findAll();
    }

    //Find book by {id} - http://localhost:8080/bookapi/findbyid/1
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/findbyid/{id}" , method = RequestMethod.GET)
    public Book findById(@PathVariable int id){
        return repository.findById(id).get();
    }

    //Update book - http://localhost:8080/bookapi/update/1
    @CrossOrigin(origins = "http://localhost:4200")
   @RequestMapping(value="/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book newBook) {
    Optional<Book> optionalBook = repository.findById(id);
    if (optionalBook.isPresent()) {
        Book existingBook = optionalBook.get();
        existingBook.setTitle(newBook.getTitle());
        existingBook.setPrice(newBook.getPrice());
        // Update other fields as needed
        
        Book updatedBook = repository.save(existingBook);
        return ResponseEntity.ok(updatedBook);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    //Delete book - http://localhost:8080/bookapi/delete/1
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/delete/{id}" , method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int id){
        repository.deleteById(id);
    }
}
