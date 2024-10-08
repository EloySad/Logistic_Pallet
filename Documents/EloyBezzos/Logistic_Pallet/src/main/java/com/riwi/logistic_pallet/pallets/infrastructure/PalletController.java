package com.riwi.logistic_pallet.pallets.infrastructure;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.riwi.logistic_pallet.common.infrastructure.dtos.response.ProblemDetailWithErrors;
import com.riwi.logistic_pallet.pallets.application.PalletService;
import com.riwi.logistic_pallet.pallets.domain.PalletEntity;
import com.riwi.logistic_pallet.pallets.infrastructure.dtos.request.PalletDto;
import com.riwi.logistic_pallet.pallets.infrastructure.dtos.response.PalletResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/pallets")
public class PalletController {

        @Autowired
        private PalletService palletService;

        @Operation(summary = "Register pallet", description = "Description: Register a new pallet")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Pallet registration is successful", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PalletResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }),
                        @ApiResponse(responseCode = "409", description = "Pallet already exists", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }), })
        @PostMapping
        public ResponseEntity<PalletResponseDto> registerPallet(@Valid @RequestBody PalletDto palletDto) {
                palletService.createPallet(palletDto);
                PalletResponseDto palletResponseDto = PalletResponseDto.builder().status(HttpStatus.CREATED.value())
                                .message("Pallet registered successfully").build();
                return ResponseEntity.status(HttpStatus.CREATED).body(palletResponseDto);
        }

        @Operation(summary = "Search pallet by id", description = "Description: Search a pallet by id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Pallet successfully found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PalletResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }),
                        @ApiResponse(responseCode = "409", description = "Pallet already exists", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }), })

        @GetMapping("{id}")
        public ResponseEntity<PalletEntity> getPalletById(Long id) {
                return palletService.getPalletById(id).map(ResponseEntity::ok)
                                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        @Operation(summary = "Search all pallets", description = "Description: Search all pallets registered")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Pallet successfully found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PalletResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }),
                        @ApiResponse(responseCode = "409", description = "Pallet already exists", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }), })

        @GetMapping()
        public List<PalletEntity> getAllPallets() {
                return palletService.getAllPallets();
        }

        @Operation(summary = "Update pallet", description = "Description: Chande the data of a pallet")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Pallet successfully updated", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PalletResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }),
                        @ApiResponse(responseCode = "409", description = "Pallet already exists", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }), })

        @PutMapping("{id}")
        public ResponseEntity<PalletResponseDto> updatePallet(@PathVariable Long id,
                        @Valid @RequestBody PalletDto palletDto) {
                palletService.updatePallet(id, palletDto);
                PalletResponseDto palletResponseDto = PalletResponseDto.builder().status(HttpStatus.CREATED.value())
                                .message("Pallet updated successfully").build();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(palletResponseDto);
        }

        @Operation(summary = "Delete pallet", description = "Description: Delete the pallet with the specified id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Pallet successfully updated", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PalletResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }),
                        @ApiResponse(responseCode = "409", description = "Pallet already exists", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }), })

        @DeleteMapping("{id}")
        public ResponseEntity<PalletResponseDto> deletePallet(@PathVariable Long id) {
                palletService.deletePalletById(id);
                PalletResponseDto palletResponseDto = PalletResponseDto.builder().status(HttpStatus.CREATED.value())
                                .message("Pallet deleted successfully").build();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(palletResponseDto);
        }

}
