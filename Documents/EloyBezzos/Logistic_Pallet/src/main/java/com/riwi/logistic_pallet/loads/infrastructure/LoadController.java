package com.riwi.logistic_pallet.loads.infrastructure;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.riwi.logistic_pallet.common.infrastructure.dtos.response.ProblemDetailWithErrors;
import com.riwi.logistic_pallet.loads.application.LoadService;
import com.riwi.logistic_pallet.loads.domain.LoadsEntity;
import com.riwi.logistic_pallet.loads.infrastructure.dtos.response.LoadResponseDto;
import com.riwi.logistic_pallet.loads.infrastructure.dtos.request.LoadDto;

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
@RequestMapping("/loads")
public class LoadController {

        @Autowired
        private LoadService loadService;

        @Operation(summary = "Register load", description = "Description: Register a new load")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Load registration is successful", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = LoadResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }),
                        @ApiResponse(responseCode = "409", description = "Load already exists", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)) }), })
        @PostMapping
        public ResponseEntity<LoadResponseDto> registerLoad(@Valid @RequestBody LoadDto loadDto) {
                loadService.createLoad(loadDto);
                LoadResponseDto loadResponseDto = LoadResponseDto.builder().status(HttpStatus.CREATED.value())
                                .message("Load registered successfully").build();
                return ResponseEntity.status(HttpStatus.CREATED).body(loadResponseDto);
        }

        @Operation(summary = "Search load by id", description = "Description: Search a load by id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Load successfully found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = LoadResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }) })

        @GetMapping("{id}")
        public ResponseEntity<LoadsEntity> getLoadById(Long id) {
                return loadService.getLoadById(id).map(ResponseEntity::ok)
                                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }

        @Operation(summary = "Search all loads", description = "Description: Search all loads registered")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Load successfully found", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = LoadResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }) })

        @GetMapping()
        public List<LoadsEntity> getAllLoads() {
                return loadService.getAllLoads();
        }

        @Operation(summary = "Update load", description = "Description: Change the data of a load")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Load successfully updated", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = LoadResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }) })

        @PutMapping("{id}")
        public ResponseEntity<LoadResponseDto> updateLoad(@PathVariable Long id,
                        @Valid @RequestBody LoadDto loadDto) {
                loadService.updateLoad(id, loadDto);
                LoadResponseDto loadResponseDto = LoadResponseDto.builder().status(HttpStatus.CREATED.value())
                                .message("Load updated successfully").build();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(loadResponseDto);
        }

        @Operation(summary = "Delete load", description = "Description: Delete the load with the specified id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Load successfully updated", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = LoadResponseDto.class)) }),
                        @ApiResponse(responseCode = "400", description = "The request body has invalid values", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetailWithErrors.class)) }) })

        @DeleteMapping("{id}")
        public ResponseEntity<LoadResponseDto> deleteLoad(@PathVariable Long id) {
                loadService.deleteLoadById(id);
                LoadResponseDto loadResponseDto = LoadResponseDto.builder().status(HttpStatus.CREATED.value())
                                .message("Load deleted successfully").build();
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(loadResponseDto);
        }

}
