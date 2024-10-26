package com.project_lp2.project_lp2.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CATEGORY")
public class ModelCategory {

    @Id
    @Column(name = "CAT_ID")
    private Long idCategory;
    
    @Column(name = "CAT_NAME", nullable = false, length = 40)
    private String categoryName;

    @OneToMany(mappedBy = "idCategory")
    private List<ModelProduct> ls_products;
}
