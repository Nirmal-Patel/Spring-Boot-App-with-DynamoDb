package com.tekion.dynamodblocaldemo.service.requestDTOs;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthorRequest {

    private String firstName;
    private String lastName;
}
