package com.example.exchangerate.controller;

import com.example.exchangerate.dto.Response;
import com.example.exchangerate.service.ExchangeRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(ExchangeRateController.class)
public class ExchangeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    ExchangeRateService exchangeRateService;

    @Test
    public void getExchangeRate() throws Exception {
        Response response = Response.builder().sourceCurrency("AUD").targetCode("INR").exchangeRate("53.17").build();
        Mockito.when(exchangeRateService.getExchangeRate("AUD","INR")).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/exchange-rate")
                        .param("baseCode", "AUD")
                        .param("targetCode", "INR"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate").value("53.17"));
    }


    @Test
    public void getAllExchangeRates() throws Exception {
        Response response = Response.builder().sourceCurrency("AUD").targetCode(null).exchangeRate("{AED=2.414787, AFN=57.295654, ALL=67.105595}").build();
        Mockito.when(exchangeRateService.getExchangeRate("AUD",null)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/exchange-rate")
                        .param("baseCode", "AUD")
                        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exchangeRate").value("{AED=2.414787, AFN=57.295654, ALL=67.105595}"));
    }

    @Test
    public void convertCurrency() throws Exception {
        Response response = Response.builder().sourceCurrency("AUD").targetCode("AED").exchangeRate("{AED=2.414787, AFN=57.295654, ALL=67.105595}").convertedAmount("241.47").build();
        Mockito.when(exchangeRateService.convertCurrency("AUD","AED","100")).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/convert-currency")
                        .param("baseCode", "AUD")
                        .param("targetCode", "AED")
                .param("amount","100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.convertedAmount").value("241.47"));
    }

    @Test
    public void convertToMultipleCurrencies() throws Exception {
        Response response = Response.builder().sourceCurrency("AUD").targetCode("AED,AFN").convertedAmount("241.49,5411.09").build();
        Mockito.when(exchangeRateService.convertCurrency("AUD","AED,AFN","100")).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/convert-currency")
                        .param("baseCode", "AUD")
                        .param("targetCode", "AED,AFN")
                        .param("amount","100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.convertedAmount").value("241.49,5411.09"));
    }

    @Test
    public void validateInputTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/convert-currency")
                        .param("baseCode", "A")
                        .param("targetCode", "AED,AFN")
                        .param("amount", "-1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }
}
