package com.amus.webserver.domain.member.member.controller;

import com.amus.webserver.domain.member.member.dto.MemberDto;
import com.amus.webserver.global.security.config.Container;
import com.amus.webserver.global.security.config.DtoContainer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @PostMapping("/sign")
    public ResponseEntity<Container> signMember(){
        DtoContainer<MemberDto> container = new DtoContainer<>(
                "등록에 성공했습니다.",
                null
        );
        return ResponseEntity.ok().body(container);
    }
}
