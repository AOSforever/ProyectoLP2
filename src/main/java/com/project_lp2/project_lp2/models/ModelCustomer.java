package com.project_lp2.project_lp2.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CUSTOMER", uniqueConstraints = @UniqueConstraint(columnNames = "CUS_DNI") ) 
public class ModelCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUS_ID")
    private Long idCustomer;

    @NotNull
    @Size(min = 8, max = 8)
    @Column(name = "CUS_DNI", nullable = false)
    private String customerDNI;

    @NotNull
    @Size(min = 6, max = 40)
    @Column(name = "CUS_NAME", nullable = false)
    private String customerName;

    @NotNull
    @Size(max = 40)
    @Column(name = "CUS_LAST_NAME", nullable = false)
    private String customerLastName;

    @Size(min = 9, max = 12)
    @Column(name = "CUS_PHONE")
    private String customerPhone;

}
