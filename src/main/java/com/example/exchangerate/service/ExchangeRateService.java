package com.example.exchangerate.service;

import com.example.exchangerate.dto.ExchangeMoney;

import com.example.exchangerate.dto.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface ExchangeRateService {

    Response getExchangeRate(String from, String to) throws JSONException;

    Response convertCurrency(String from,String to,String amount) throws JSONException;
}
