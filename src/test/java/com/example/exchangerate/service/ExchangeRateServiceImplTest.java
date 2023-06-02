package com.example.exchangerate.service;

import com.example.exchangerate.dto.ExchangeMoney;

import com.example.exchangerate.dto.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceImplTest {
    @InjectMocks
    ExchangeRateService exchangeRateService = new ExchangeRateServiceImpl();

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetExchangeRateFromSourceToTarget() throws JSONException {
        Map<String,Double> rates = new HashMap<>();
        rates.put("INR",53.71);
        rates.put("EUR",0.61);
        ExchangeMoney exchangeMoney = new ExchangeMoney();
        exchangeMoney.setRates(rates);
        Mockito.when(restTemplate.getForObject("https://api.exchangerate.host/latest?base={from}", ExchangeMoney.class,"AUD")).thenReturn(exchangeMoney);
        Response res = exchangeRateService.getExchangeRate("AUD","INR");
        Assertions.assertEquals(res.getExchangeRate(),"53.71");
    }

    @Test
    public void testGetAllExchangeRateForSourceCurrency() throws JSONException {
        Map<String,Double> rates = new HashMap<>();
        rates.put("INR",53.71);
        rates.put("EUR",0.61);
        ExchangeMoney exchangeMoney = new ExchangeMoney();
        exchangeMoney.setRates(rates);
        Mockito.when(restTemplate.getForObject("https://api.exchangerate.host/latest?base={from}", ExchangeMoney.class,"AUD")).thenReturn(exchangeMoney);
        Response res = exchangeRateService.getExchangeRate("AUD",null);
        Assertions.assertEquals(res.getExchangeRate(),rates.toString());
    }

    @Test
    public void testConversionOfCurrencyValue() throws JSONException {
        Map<String,Double> rates = new HashMap<>();
        rates.put("INR",53.71);
        rates.put("EUR",0.61);
        ExchangeMoney exchangeMoney = new ExchangeMoney();
        exchangeMoney.setRates(rates);
        Mockito.when(restTemplate.getForObject("https://api.exchangerate.host/latest?base={from}", ExchangeMoney.class,"AUD")).thenReturn(exchangeMoney);
        Response res = exchangeRateService.convertCurrency("AUD","EUR","100");
        Assertions.assertEquals(res.getConvertedAmount(),"61.00");
    }

    @Test
    public void testConversionOfMultipleCurrencyValue() throws JSONException {
        Map<String,Double> rates = new HashMap<>();
        rates.put("INR",53.71);
        rates.put("EUR",0.61);
        ExchangeMoney exchangeMoney = new ExchangeMoney();
        exchangeMoney.setRates(rates);
        Mockito.when(restTemplate.getForObject("https://api.exchangerate.host/latest?base={from}", ExchangeMoney.class,"AUD")).thenReturn(exchangeMoney);
        Response res = exchangeRateService.convertCurrency("AUD","EUR,INR","100");
        Assertions.assertEquals(res.getConvertedAmount(),"61.00,5371.00");
    }
}
