package com.riwi.logistic_pallet.loads.infrastructure.dtos.response;

import com.riwi.logistic_pallet.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LoadResponseDto extends ApiResponseDto {

  private LoadResponseDto(int value, String string) {
    super(value, string);
  }
}
