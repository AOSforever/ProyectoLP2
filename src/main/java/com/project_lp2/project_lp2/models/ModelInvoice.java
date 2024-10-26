package com.project_lp2.project_lp2.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "SUPPLIER_INVOICE")
public class ModelInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INV_ID")
    private Long idInvoice;

    @ManyToOne
    @JoinColumn(name = "SUP_ID", nullable = false)
    private ModelSupplier idSupplier;

    @ManyToOne
    @JoinColumn(name = "EMP_ID", nullable = false)
    private ModelEmployee idEmployee;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "INV_DATE_ISSUE")
    private Date invoiceDateIssue;

    public enum EnumInvoicePaymentMethod {
        CREDITO,
        DEBITO,
        EFECTIVO
    }

    @Column(name = "INV_PAYMENT_METHOD")
    @Enumerated(EnumType.STRING)
    private EnumInvoicePaymentMethod invoicePaymentMethod;

    @ManyToMany
    @JoinTable(
        name = "SUPPLIER_INVOICE_DETAILS",
        joinColumns = @JoinColumn(name = "INV_ID"),
        inverseJoinColumns = @JoinColumn(name = "PROD_ID")
    )
    private List<ModelProduct> ls_products;
}
