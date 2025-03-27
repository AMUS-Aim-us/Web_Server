package com.amus.webserver.domain.member.member.service;

import com.amus.webserver.domain.member.member.dto.MemberResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public MemberResponseDto registerMember(String email, String name, String nickname, String password) {
        return new MemberResponseDto(email, name, nickname);
    }
}
