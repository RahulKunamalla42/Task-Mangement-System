package com.TMS.TASK_SERVICE.services;

import com.TMS.TASK_SERVICE.entities.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE",url = "http://localhost:5001")
public interface  UserService {

    @GetMapping("/api/profile")
    UserDto getProfile(@RequestHeader("Authorization") String jwt);
}
