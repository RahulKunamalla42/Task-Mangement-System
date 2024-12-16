package com.TMS.SUBMISSION_SERVICE.services;

import com.TMS.SUBMISSION_SERVICE.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE",url = "http://localhost:5001")
public interface UserService {

    @GetMapping("/api/profile")
    UserDto getProfile(@RequestHeader("Authorization") String jwt);
}
