package com.example.exchangerate.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class ExchangeMoney {

    public Map<String, Double> rates;
    public String base;
    public Date  date;
    public String error;
}
