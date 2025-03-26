package com.amus.webserver.domain.member.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @PostMapping("/sign")
    public ResponseEntity signMember(){
        return ResponseEntity.ok().body(null);
    }
}
