package com.tekion.dynamodblocaldemo.service.requestDTOs;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class BookLendRequest {

    private List<String> bookIds;
    private String memberId;
}
