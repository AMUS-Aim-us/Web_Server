package com.amus.webserver.domain.member.member.controller;

import com.amus.webserver.domain.member.member.dto.MemberRegisterRequestDto;
import com.amus.webserver.domain.member.member.dto.MemberResponseDto;
import com.amus.webserver.domain.member.member.service.MemberService;
import com.amus.webserver.global.security.config.Container;
import com.amus.webserver.global.security.config.DtoContainer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {

    private final MemberService memberService;

    public MemberAPIController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/sign")
    public ResponseEntity<Container> signMember(@RequestBody MemberRegisterRequestDto dto){

        MemberResponseDto responseDto = memberService.registerMember(dto.getEmail(), dto.getName(), dto.getNickname(), dto.getPassword());
        DtoContainer<MemberResponseDto> container = new DtoContainer<>(
                "등록에 성공했습니다.",
                responseDto
        );
        return ResponseEntity.ok().body(container);
    }
}
