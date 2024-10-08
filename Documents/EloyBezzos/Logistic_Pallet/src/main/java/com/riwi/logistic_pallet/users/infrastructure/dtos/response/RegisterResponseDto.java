package com.riwi.logistic_pallet.users.infrastructure.dtos.response;

import com.riwi.logistic_pallet.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RegisterResponseDto extends ApiResponseDto {
  public RegisterResponseDto(int value, String string) {
    super(value, string);
  }
}