package com.tekion.dynamodblocaldemo.service.beans;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.tekion.dynamodblocaldemo.service.requestDTOs.MemberRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@DynamoDBTable(tableName = "member")
public class Member {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String firstName;

    @DynamoDBAttribute
    private String lastName;

    @DynamoDBAttribute
    @DynamoDBTypeConvertedEnum
    private MemberStatus status;

    public static Member fromRequest(MemberRequest memberRequest){
        return new Member()
                .setFirstName(memberRequest.getFirstName())
                .setLastName(memberRequest.getLastName())
                .setStatus(MemberStatus.ACTIVE);
    }
}