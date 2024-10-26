package com.project_lp2.project_lp2.models;

import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "CATEGORY")
public class ModelCategory {

    @Id
    @Column(name = "CAT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    
    @Column(name = "CAT_NAME", nullable = false, length = 40)
    private String categoryName;

    @OneToMany(mappedBy = "idCategory")
    private List<ModelProduct> ls_products;
}
