package com.amus.webserver.domain.member.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String email;
    private String name;
    private String nickname;
    private String address;
    private String phoneNumber;

    public MemberDto(){}

    public MemberDto(String email, String name, String nickname, String address, String phoneNumber){
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
