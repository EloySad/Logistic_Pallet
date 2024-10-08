package com.riwi.logistic_pallet.pallets.infrastructure.dtos.request;

import com.riwi.logistic_pallet.common.infrastructure.validators.enums.ValidEnum;
import com.riwi.logistic_pallet.pallets.domain.StatesPallets;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PalletDto {
    @Schema(example = "8", description = "Max capacity in kg of the pallet")
    @Min(value = 1, message = "Max capacity must be greater than zero")
    private Long maxCapacity;

    @Schema(example = "Zone 15", description = "Location of the pallet")
    @NotBlank(message = "location cannot be blank")
    private String location;

    @Schema(example = "AVAILABLE", description = "State of the pallet", implementation = StatesPallets.class)
    @NotBlank(message = "State cannot be blank")
    @ValidEnum(enumClass = StatesPallets.class, message = "State must be either 'AVAILABLE' or 'USING' or 'DAMAGED'")
    private String state;

}
