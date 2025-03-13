package com.student.student_service.feignclient;


import com.student.student_service.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/*** interface to declare feign client to call address-service api ***/

@FeignClient(url = "${address.service.url}",value = "address-feign-client",path = "/api/address")
public interface AddressFeignClient {

    /*** mapping from address controller in the declarative way ***/

    @GetMapping("/get/{id}")
    public AddressResponse getAddress(@PathVariable long id);


}