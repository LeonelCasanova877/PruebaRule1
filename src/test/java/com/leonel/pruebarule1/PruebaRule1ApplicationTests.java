package com.leonel.pruebarule1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonel.pruebarule1.controller.AccountController;
import com.leonel.pruebarule1.dto.ErrorDTO;
import com.leonel.pruebarule1.exception.Rule1Exception;
import com.leonel.pruebarule1.exception.Rule1ExceptionHandler;
import com.leonel.pruebarule1.model.Account;
import com.leonel.pruebarule1.repository.AccountTypeRepository;
import com.leonel.pruebarule1.service.AccountService;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {AccountController.class, Rule1ExceptionHandler.class})
@EnableWebMvc
@AutoConfigureMockMvc
class PruebaRule1ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AccountService accountService;


    @Test
    void findAccountsByTypeAndAccountTypeExistAndReturnListOfAccounts() throws Exception {

        String accountTypeName = "nombreTest";
        List<Account> accountList = new ArrayList<>();
        given(accountService.findAccountsByType(accountTypeName)).willReturn(accountList);

        MockHttpServletResponse response = mockMvc.perform(get("/accounts?accountTypeName=nombreTest")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertNotNull(response.getContentAsString());
    }

    @Test
    void findAccountsByTypeAndAccountTypeDoesNotExistAndReturnError() throws Exception {

        String accountTypeName = "nombreTest";
        given(accountService.findAccountsByType(accountTypeName))
                .willThrow(new Rule1Exception(new ErrorDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "account type not found"
        ), HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/accounts?accountTypeName=nombreTest")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }


}
