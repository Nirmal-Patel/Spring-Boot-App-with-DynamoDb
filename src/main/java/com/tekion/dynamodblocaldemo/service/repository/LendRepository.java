package com.tekion.dynamodblocaldemo.service.repository;

import com.tekion.dynamodblocaldemo.service.beans.Book;
import com.tekion.dynamodblocaldemo.service.beans.Lend;
import com.tekion.dynamodblocaldemo.service.beans.LendStatus;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableScan
public interface LendRepository extends CrudRepository<Lend,String> {

    Optional<Lend> findByBookIdAndStatus(String bookId, LendStatus status);

}
