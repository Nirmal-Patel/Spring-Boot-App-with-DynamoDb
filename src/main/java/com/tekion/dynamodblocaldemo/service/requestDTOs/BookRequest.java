package com.tekion.dynamodblocaldemo.service.requestDTOs;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BookRequest {

    private String name;
    private String isbn;
    private String authorId;
}
