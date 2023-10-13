package com.wcs.restapi.repository;

import com.wcs.restapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByTitleContainingOrDescriptionContaining(String titleSearchTerm, String descriptionSearchTerm);
}
