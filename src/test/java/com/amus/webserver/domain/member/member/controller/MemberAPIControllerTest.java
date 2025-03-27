package com.amus.webserver.domain.member.member.controller;

import com.amus.webserver.domain.member.member.dto.MemberRegisterRequestDto;
import com.amus.webserver.domain.member.member.dto.MemberResponseDto;
import com.amus.webserver.domain.member.member.service.MemberService;
import com.amus.webserver.global.security.config.DtoContainer;
import com.amus.webserver.global.security.config.Container;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.lang.reflect.Member;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.Assert.state;


@WebMvcTest(controllers = MemberAPIController.class)
public class MemberAPIControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockitoBean
    private MemberService memberService;

    @PostConstruct
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new MemberAPIController(memberService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("sign member")
    public void signMemberTest() throws Exception {
        //given
        MemberRegisterRequestDto content = new MemberRegisterRequestDto(
                "user1@gmail.com",
                "user1",
                "firstUser",
                "user123456"
        );

        // when
        when(memberService.registerMember("user1@gmail.com", "user1", "firstUser", "user123456"))
                .thenReturn(new MemberResponseDto("user1@gmail.com", "user1", "firstUser"));


        MvcResult result = mockMvc.perform(
                        post("/api/member/sign")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(content))
                )
                .andExpect(status().isOk())
                .andReturn();

        // then
        DtoContainer dto = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), DtoContainer.class);
        MemberResponseDto responseDto = changeContent((Map) dto.getData(), MemberResponseDto.class);

        assertThat(dto.getResultMsg()).as("등록 성공 여부 확인").isEqualTo("등록에 성공했습니다.");
        assertThat(responseDto.getEmail()).as("등록 내용 확인").isEqualTo("user1@gmail.com");
        assertThat(responseDto.getName()).as("등록 내용 확인").isEqualTo("user1");
        assertThat(responseDto.getNickname()).as("등록 내용 확인").isEqualTo("firstUser");
    }

    public <T> T changeContent(Map content, Class<T> type) throws JsonProcessingException {
        String str = objectMapper.writeValueAsString(content);
        return objectMapper.readValue(str,type);
    }
}
