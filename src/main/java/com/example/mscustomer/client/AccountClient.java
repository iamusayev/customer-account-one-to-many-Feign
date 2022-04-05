package com.example.mscustomer.client;

import com.example.mscustomer.client.model.AccountDecoder;
import com.example.mscustomer.client.model.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "customer-account",
        url = "${feign.client.config.account.url}",
        configuration = AccountDecoder.class
)
public interface AccountClient {

    @GetMapping("/{id}")
    List<AccountResponseDto> getAllAccountsByCustomerId(@PathVariable Long id);

}
