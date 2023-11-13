package com.trip.controller;

import com.trip.member.MemberDto;
import com.trip.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody Map<String, Object> map) {
        log.debug(map.get("id").toString());
        log.debug(map.get("password").toString());
        return ResponseEntity.ok().build();
    }
}
