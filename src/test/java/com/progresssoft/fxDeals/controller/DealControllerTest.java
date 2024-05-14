package com.progresssoft.fxDeals.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progresssoft.fxDeals.model.Deal;
import com.progresssoft.fxDeals.service.DealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(DealController.class)
public class DealControllerTest {

    @MockBean
    private DealService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void CreateDeal_success() throws Exception {
        Deal newDeal = new Deal();
        newDeal.setDealId(UUID.randomUUID());
        newDeal.setFromCurrency("EUR");
        newDeal.setToCurrency("USD");
        newDeal.setDealTime(Instant.now());
        newDeal.setAmount(85.36);

        Mockito.when(service.SaveDeal(newDeal)).thenReturn(newDeal);

        ResultActions res = mockMvc.perform(post("/api/create/deal").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(newDeal)));

        res.andExpect(MockMvcResultMatchers.status().isCreated());
    }


}