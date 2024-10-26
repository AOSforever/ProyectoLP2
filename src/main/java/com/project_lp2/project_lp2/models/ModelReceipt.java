package com.project_lp2.project_lp2.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "RECEIPT")
public class ModelReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID")
    private Long idReceipt;

    @ManyToOne
    @JoinColumn(name = "EMP_ID", nullable = false)
    private ModelEmployee idEmployee;

    @ManyToOne
    @JoinColumn(name = "CUS_ID", nullable = false)
    private ModelCustomer idCustomer;

    public enum EnumReceiptType {
        CREDITO,
        DEBITO,
        EFECTIVO
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "REC_PAYMENT_MT")
    private EnumReceiptType receiptPaymentMethod;

    @Column(name = "REC_DATE_ISSUE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receiptDateIssue;

    @ManyToMany
    @JoinTable(
        name = "RECEIPT_DETAILS",
        joinColumns = @JoinColumn(name = "REC_ID"),
        inverseJoinColumns = @JoinColumn(name = "PROD_ID")
    )
    private List<ModelProduct> ls_products;

}
