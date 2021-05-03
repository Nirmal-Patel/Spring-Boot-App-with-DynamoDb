package com.tekion.dynamodblocaldemo.service.beans;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.tekion.dynamodblocaldemo.service.requestDTOs.BookRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@DynamoDBTable(tableName = "book")
public class Book {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String name;

    @DynamoDBAttribute
    private String isbn;

    @DynamoDBAttribute
    private String authorId;

    public static Book fromRequest(BookRequest bookRequest){
        return new Book()
                .setName(bookRequest.getName())
                .setAuthorId(bookRequest.getAuthorId())
                .setIsbn(bookRequest.getIsbn());
    }
}
