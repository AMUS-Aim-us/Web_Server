package com.amus.webserver.domain.member.member.controller;

import com.amus.webserver.global.security.config.DtoContainer;
import com.amus.webserver.global.security.config.Container;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.Assert.state;


@WebMvcTest(controllers = MemberAPIController.class)
public class MemberAPIControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup().build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("sign member")
    public void signMemberTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/member/sign"))
                .andExpect(status().isOk())
                .andReturn();

        Container dto = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), DtoContainer.class);

        assertThat(dto.getResultMsg()).as("등록 성공 여부 확인").isEqualTo("등록에 성공했습니다.");
        assertThat(dto.getData()).as("등록 성공 여부 확인").isNull();
    }
}
