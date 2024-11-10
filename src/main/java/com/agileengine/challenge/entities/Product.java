package com.agileengine.challenge.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    /**
     * TODO: Add inventory module to handle purchase and selling price, also to add discounts, etc.
     */
    @Column
    private BigDecimal price;

    /**
     * TODO: add inventory tables and relationships in order to have a control over our products
     * Right now we will not handle products by the score, so if we want to sell 100mg of anything we
     * would need to create a product called X 500mg, this it not the best approach however in order to keep
     * the challenge scope small is good, this MVP version
    **/
    @Column
    private Integer stock;

}
