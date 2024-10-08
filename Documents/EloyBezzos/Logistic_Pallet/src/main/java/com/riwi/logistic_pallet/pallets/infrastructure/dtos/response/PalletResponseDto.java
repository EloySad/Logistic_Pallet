package com.riwi.logistic_pallet.pallets.infrastructure.dtos.response;

import com.riwi.logistic_pallet.common.infrastructure.dtos.response.ApiResponseDto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class PalletResponseDto extends ApiResponseDto {

  private PalletResponseDto(int value, String string) {
    super(value, string);
  }
}
