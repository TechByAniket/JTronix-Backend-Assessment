package com.jtronix.employee_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequest {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotBlank(message = "Department must not be empty")
    private String department;
}
