package com.trip.controller;

import com.trip.member.MemberDto;
import com.trip.member.service.MemberService;
import com.trip.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> doLogin(@RequestBody Map<String, Object> map) throws Exception {
        log.debug(map.get("id").toString());
        log.debug(map.get("password").toString());
        
        String id = (String) map.get("id");
        String password = (String) map.get("password");
        
        MemberDto hasFound = memberService.login(id, password);
        
        if(hasFound != null) {
        	String jwt = JwtUtil.createJwt(id, 1000 * 60 * 60L); // 1시간
        	return ResponseEntity.ok().body(jwt);
        }
        
        return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }
}
