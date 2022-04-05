package com.example.mscustomer.model.dto;

import com.example.mscustomer.client.model.AccountResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class CustomerWithAllAccounts {

    private String name;
    private String lastname;
    private Integer age;
    private List<AccountResponseDto> accounts;

}
