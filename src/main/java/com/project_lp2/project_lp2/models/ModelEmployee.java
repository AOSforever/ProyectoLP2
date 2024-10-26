package com.project_lp2.project_lp2.models;

import jakarta.persistence.Entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
    name = "EMPLOYEE", 
    uniqueConstraints = @UniqueConstraint(columnNames = "EMP_DNI, EMP_USER_NAME")
)
public class ModelEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long idEmployee;

    @Column(name = "EMP_DNI", unique = true, nullable = false, length = 8)
    @Size(min = 8, max = 8)
    private String employeeDNI;

    @Column(name = "EMP_NAME", nullable = false, length = 40)
    @Size(min = 5, max = 40)
    private String employeeName;

    @Column(name = "EMP_LAS_NAME", nullable = false, length = 40)
    private String employeeLastName;

    @Column(name = "EMP_PHONE", nullable = false, length = 12)
    @Size(min = 9, max = 12)
    private String employeePhone;

    @Column(name = "EMP_EMAIL", nullable = false, length = 40)
    @Email
    private String employeeEmail;

    @Column(name = "EMP_ADDRESS", nullable = false, length = 40)
    private String employeeAddress;

    @Column(name = "EMP_USER_TYPE", nullable = false, length = 20)
    private String employeeUserType;

    @Column(name = "EMP_USER_NAME", unique = true, nullable = false, length = 20)
    private String employeeUserName;

    @Column(name = "EMP_USER_PSW", nullable = false, length = 20)
    @Size(min = 5, max = 20)
    private String employeeUserPassword;

    @Lob
    @Column(name = "EMP_USER_PROFILE")
    private byte[] employeeUserProfile;

    @OneToMany(mappedBy = "idEmployee")
    private List<ModelReceipt> ls_receipts;

    @OneToMany(mappedBy = "idEmployee")
    private List<ModelInvoice> ls_invoices;

}
