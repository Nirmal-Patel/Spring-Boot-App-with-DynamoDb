package com.tekion.dynamodblocaldemo.service.repository;

import com.tekion.dynamodblocaldemo.service.beans.Book;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableScan
public interface BookRepository extends CrudRepository<Book,String> {

    Optional<Book> findByIsbn(String isbn);

}
