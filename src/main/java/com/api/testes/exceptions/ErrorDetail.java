package com.api.testes.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;
}
