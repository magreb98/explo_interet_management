package com.explorateurmboa.interet_management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feign-user", url = "localhost:8084/api/users")
public interface FeignUser {

    @RequestMapping(method = RequestMethod.GET, name = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id);
}
