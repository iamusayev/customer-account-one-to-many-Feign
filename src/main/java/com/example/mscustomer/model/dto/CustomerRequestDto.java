package com.example.mscustomer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
public class CustomerRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    @NotNull
    @Max(value = 70)
    @Min(value = 16)
    private Integer age;

    @Email
    private String email;

}
