package com.example.exchangerate.service;

import com.example.exchangerate.dto.ExchangeMoney;
import com.example.exchangerate.dto.Response;
import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Response getExchangeRate(String sourceCurrency,String targetCode) throws JSONException {
        Map<String,Double> rates = fetchExchangeRateForSourceCurrency(sourceCurrency);
       if(Objects.isNull(targetCode)){
            return Response.builder().sourceCurrency(sourceCurrency).targetCode("all").exchangeRate(rates.toString()).build();
        }
        return Response.builder().sourceCurrency(sourceCurrency).targetCode(targetCode).exchangeRate(fetchExchangeRateFortargetCode(rates,targetCode).toString()).build();
    }


  @Override
    public Response convertCurrency(String sourceCurrency,String targetCode, String amount) throws JSONException {
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> list = new ArrayList<>();
        List<String> toCurrencies = Arrays.stream(targetCode.split(",")).distinct().collect(Collectors.toList());
        Map<String,Double> rates = fetchExchangeRateForSourceCurrency(sourceCurrency);
        if(toCurrencies.size() == 1) {
            Double exchangeRate =fetchExchangeRateFortargetCode(rates,targetCode);
            return Response.builder().sourceCurrency(sourceCurrency).targetCode(targetCode)
                    .convertedAmount(String.valueOf(df.format(
                            exchangeRate*Integer.parseInt(amount)))).build();
        } else if(toCurrencies.size()>1){
            for(String toCurrency : toCurrencies){
                list.add(df.format(fetchExchangeRateFortargetCode(rates,toCurrency)
                        *Integer.parseInt(amount)));
            }
            return Response.builder().sourceCurrency(sourceCurrency).targetCode(targetCode).convertedAmount(list.stream().collect(Collectors.joining(","))).build();
        }

    return null;
    }

    public Map<String,Double> fetchExchangeRateForSourceCurrency(String from) throws JSONException {
        String uri="https://api.exchangerate.host/latest?base={from}";
        ExchangeMoney result = restTemplate.getForObject(uri, ExchangeMoney.class,from);
        return result.getRates();

    }
    private Double fetchExchangeRateFortargetCode(Map<String,Double> rates, String to) {

        return rates.entrySet().stream().filter(m->m.getKey().equalsIgnoreCase(to)).map(m->m.getValue()).findFirst().orElseThrow(()->new IllegalArgumentException("Please provide the valid currency"));

    }



}
