package com.utkarsh2573.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold Successful Response Information"
)
@Data @AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "Status code of response",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Status message of response",
            example = "Request Processed Successfully"
    )
    private String statusMsg;

}
