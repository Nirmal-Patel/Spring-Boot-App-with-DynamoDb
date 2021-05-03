package com.tekion.dynamodblocaldemo.service.repository;

import com.tekion.dynamodblocaldemo.service.beans.Member;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface MemberRepository extends CrudRepository<Member,String> {
}
