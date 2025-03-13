package com.student.student_service.feignclient;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(value = "addressmicroservice")
public class AdressServiceLoadConfig {

    @LoadBalanced
    @Bean
    public Feign.Builder builder(){
        return Feign.builder();
    }

}
