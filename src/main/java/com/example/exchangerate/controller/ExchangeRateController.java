package com.example.exchangerate.controller;



import com.example.exchangerate.dto.Response;
import com.example.exchangerate.exception.ExchangeRateException;
import com.example.exchangerate.service.ExchangeRateService;
import io.swagger.annotations.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@Api(value="Exchange Rate Controller",description="REST APIs for Currency Exchange")
public class ExchangeRateController {
    @Autowired
    ExchangeRateService exchangeRateService;
    @ApiOperation(value = "Fetch exchange rates between currencies", response = Response.class, tags = "GetExchangeRate")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found!") })
    @GetMapping(value="/exchange-rate")
    public ResponseEntity<?> getExchangeRate(@ApiParam(name="baseCode",type="String",value="Base Currency",example="AUD")@RequestParam String baseCode,@ApiParam(name="targetCode",type="String",value="Target Currency",example="EUR")@RequestParam(name="targetCode",required = false) String targetCode) throws JSONException{
        if(validateInputs(baseCode,targetCode)) {
            throw new IllegalArgumentException("Please provide the valid currency details");
        }
        return new ResponseEntity<>(exchangeRateService.getExchangeRate(baseCode, targetCode), HttpStatus.OK);

    }

   @ApiOperation(value = "Value Conversion between currencies", response = Response.class, tags = "ConvertCurrency")
   @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Success|OK"),
           @ApiResponse(code = 400, message = "Bad Request"),
           @ApiResponse(code = 404, message = "Not Found!") })
    @GetMapping(value="/convert-currency")
    public ResponseEntity<?> convertCurrency(@ApiParam(name="baseCode",type="String",value="Base Currency",example="AUD") @RequestParam String baseCode, @ApiParam(name="targetCode",type="String",value="Target Currency",example="INR") @RequestParam String targetCode,@ApiParam(name="amount",type="String",value="Please enter the amount",example="100") @RequestParam String amount) throws JSONException {
        if(Objects.isNull(baseCode) || !baseCode.matches("^[A-Z]{3}$") || Objects.isNull(targetCode) || Integer.parseInt(amount)< 0) {
            throw new IllegalArgumentException("Please provide the valid details");

        }
        return new ResponseEntity<>(exchangeRateService.convertCurrency(baseCode,targetCode,amount), HttpStatus.OK);
    }

    private boolean validateInputs(String from,String to) {
        return Objects.isNull(from) || !from.matches("^[A-Z]{3}$") || (!Objects.isNull(to) && !to.matches("^[A-Z]{3}$"));

        }





}
