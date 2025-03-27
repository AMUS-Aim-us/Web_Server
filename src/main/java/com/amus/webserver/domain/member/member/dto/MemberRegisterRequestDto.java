package com.amus.webserver.domain.member.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegisterRequestDto {
    private String email;
    private String name;
    private String nickname;
    private String password;

    public MemberRegisterRequestDto(){}

    public MemberRegisterRequestDto(String email, String name, String nickname, String password){
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
    }
}
