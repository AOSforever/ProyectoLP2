package com.project_lp2.project_lp2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOModelEmployee {
    private Long idEmployee;
    private String employeeDNI;
    private String employeeName;
    private String employeeLastName;
    private String employeePhone;
    private String employeeEmail;
    private String employeeAddress;
    private String employeeUserType;
    private String employeeUserName;
    private String employeeUserPassword;
    private String employeeUserProfile;
}
