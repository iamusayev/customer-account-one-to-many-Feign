package com.example.mscustomer.service;

import com.example.mscustomer.client.AccountClient;
import com.example.mscustomer.client.model.AccountResponseDto;
import com.example.mscustomer.dao.CustomerEntity;
import com.example.mscustomer.dao.CustomerRepository;
import com.example.mscustomer.exception.NotFoundException;
import com.example.mscustomer.mapper.CustomerMapper;
import com.example.mscustomer.model.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;

import static com.example.mscustomer.model.constants.ExceptionConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountClient accountClient;


    public void createCustomer(CustomerRequestDto dto) {
        log.info("ActionLog.createCustomer.start");
        var entity = CustomerMapper.INSTANCE.mapRequestDtoToEntity(dto);
        customerRepository.save(entity);
        log.info("ActionLog.createCustomer.success");
    }


    public CustomerResponseDto getCustomerById(Long id) {
        log.info("ActionLog.getCustomer.start id: {}", id);
        var entity = fetchCustomerIfExist(id);
        var dto = CustomerMapper.INSTANCE.mapEntityToResponseDto(entity);
        log.info("ActionLog.getCustomer.success id: {}", id);
        return dto;
    }


    public CustomerWithAllAccounts getCustomerWithAllAccounts(Long id) {
        log.info("ActionLog.getCustomerWithAllAccounts.start id: {}", id);
        List<AccountResponseDto> allAccountsByCustomerId = accountClient.getAllAccountsByCustomerId(id);
        var customerEntity = fetchCustomerIfExist(id);
        var customerWithAllAccounts = CustomerWithAllAccounts.builder()
                .accounts(allAccountsByCustomerId)
                .name(customerEntity.getName())
                .lastname(customerEntity.getLastname())
                .age(customerEntity.getAge())
                .build();
        log.info("ActionLog.getCustomerWithAllAccounts.success id: {}", id);
        return customerWithAllAccounts;
    }


        private CustomerEntity fetchCustomerIfExist(Long id) {
            return customerRepository.findById(id).orElseThrow(() -> {
                log.error("ActionLog.fetchCustomerIfExist.error id: {}", id);
                throw new NotFoundException(String.format(NOT_FOUND_EXCEPTION_MESSAGE, "Customer", "", id), NOT_FOUND_EXCEPTION_CODE);
            });
    }


}
