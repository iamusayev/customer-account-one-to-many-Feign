package com.example.mscustomer.controller;


import com.example.mscustomer.model.dto.CustomerRequestDto;
import com.example.mscustomer.model.dto.CustomerResponseDto;
import com.example.mscustomer.model.dto.CustomerWithAllAccounts;
import com.example.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CustomerRequestDto dto) {
        customerService.createCustomer(dto);
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/v1/accounts/{id}")
    public CustomerWithAllAccounts getCustomerWithAllAccounts(@PathVariable Long id) {
        return customerService.getCustomerWithAllAccounts(id);
    }

}
