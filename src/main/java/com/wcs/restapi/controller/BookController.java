package com.wcs.restapi.controller;

import com.wcs.restapi.entity.Book;
import com.wcs.restapi.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {

    private BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> index() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable(value = "id") Long id) {
        Optional<Book> optionalBook = repository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return book;
        } else {
            return null;
        }
    }

    @PostMapping("/books/{search}")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable(value = "id") Long id, @RequestBody Book book) {
        Optional<Book> optionalBookToUpdate = repository.findById(id);
        if (optionalBookToUpdate.isPresent()) {
            Book bookToUpdate = optionalBookToUpdate.get();

            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setAuthor(book.getAuthor());

            return repository.save(bookToUpdate);
        } else {
            return null;
        }
    }

    @DeleteMapping("/books/{id}")
    public boolean delete (@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
        return true;
    }
}
