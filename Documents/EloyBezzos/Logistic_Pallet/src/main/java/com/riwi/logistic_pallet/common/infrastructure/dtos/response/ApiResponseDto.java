package com.riwi.logistic_pallet.common.infrastructure.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public abstract class ApiResponseDto {
    private Integer status;
    private String message;
}
