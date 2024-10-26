package com.project_lp2.project_lp2.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "SUPPLIER", uniqueConstraints = @UniqueConstraint(columnNames = "SUP_RUC") )
public class ModelSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUP_ID")
    private Long idSupplier;

    @Column(name = "SUP_RUC", nullable = false, unique = true, length = 11)
    @NotNull
    @Length(min = 11, max = 11)
    private String supplierRUC;

    @Column(name = "SUP_NAME", nullable = false, length = 40)
    @NotNull
    @Length(min = 6, max = 40)
    private String supplierName;

    @Column(name = "SUP_PHONE", nullable = false, length = 9)
    @NotNull
    @Length(min = 9, max = 9)
    private String supplierPhone;

    @Column(name = "SUP_ADDRESS", nullable = false, length = 40)
    @NotNull
    @Length(max = 40)
    private String supplierAddress;

    @Column(name = "SUB_WEB_LINK", length = 40)
    @Pattern(regexp = "^http.*")
    private String supplierWebLink;

    @OneToMany(mappedBy = "idSupplier")
    private List<ModelInvoice> ls_invoices;
}
