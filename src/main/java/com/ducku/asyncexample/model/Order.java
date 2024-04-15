package com.ducku.asyncexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    private String trackingId;
    private Long productId;
    private String name;
    private String type;
    private int quantity;
    private BigDecimal price;


}
