package com.tekion.dynamodblocaldemo.service.repository;

import com.tekion.dynamodblocaldemo.service.beans.Author;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface AuthorRepository extends CrudRepository<Author,String> {
}
