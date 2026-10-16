package com.jtronix.employee_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeStatusRequest {

    @NotBlank(message = "Status must not be empty")
    private String status;
}