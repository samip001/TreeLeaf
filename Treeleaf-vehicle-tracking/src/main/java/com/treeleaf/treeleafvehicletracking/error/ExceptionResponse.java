package com.treeleaf.treeleafvehicletracking.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {

    private String timestamp;
    private String message;
    private String details;
    private int status;

}
