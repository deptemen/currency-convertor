package com.example.exchangerate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    String sourceCurrency;
    String targetCode;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    String exchangeRate;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    String convertedAmount;


}
