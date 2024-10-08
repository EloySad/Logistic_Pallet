package com.riwi.logistic_pallet.users.infrastructure.dtos.response;

import com.riwi.logistic_pallet.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LoginResponseDto extends ApiResponseDto {
  private LoginResponseDataDto data;

  private LoginResponseDto(int value, String string, LoginResponseDataDto data) {
    super(value, string);
    this.data = data;
  }
}
