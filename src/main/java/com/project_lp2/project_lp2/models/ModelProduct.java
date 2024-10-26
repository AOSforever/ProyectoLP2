package com.project_lp2.project_lp2.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "PRODUCT", uniqueConstraints = @UniqueConstraint(columnNames = "PROD_NAME"))
public class ModelProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_ID")
    private Long idProduct;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "CAT_ID", nullable = false)
    private ModelCategory idCategory;

    @NotNull
    @Size(min = 6, max = 30)
    @Column(name = "PROD_NAME", nullable = false, length = 30)
    private String productName;

    @NotNull
    @Min(0)
    @Column(name = "PROD_STOK_MIN", nullable = false)
    private int prodStokMin;

    @NotNull
    @Min(0)
    @Column(name = "PROD_STOCK_MAX", nullable = false)
    private int prodStockMax;

    @NotNull
    @Min(0)
    @Column(name = "PROD_STOCK", nullable = false)
    private int prodStock;

    @NotNull
    @Size(min = 6, max = 30)
    @Column(name = "PROD_DESC", nullable = false, length = 30)
    private String prodDesc;

    @NotNull
    @Column(name = "PROD_UNIT_PUR_PRICE", nullable = false)
    private double prodUnitPurPrice;

    @NotNull
    @Column(name = "PROD_UNIT_SALE_PRICE", nullable = false)
    private double prodUnitSalePrice;

    @ManyToMany(mappedBy = "ls_products")
    private List<ModelReceipt> ls_receipts;

    @ManyToMany(mappedBy = "ls_products")
    private List<ModelInvoice> ls_invoices;

}
