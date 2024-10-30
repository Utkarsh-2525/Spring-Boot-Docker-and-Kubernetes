package com.utkarsh2573.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold Error Response Information"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API Path invoked by Client"
    )
    private String ApiPath;

    @Schema(
            description = "Error code describing the error"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message describing the error"
    )
    private String errorMsg;

    @Schema(
            description = "Error message the time of error"
    )
    private LocalDateTime errorTime;
}
