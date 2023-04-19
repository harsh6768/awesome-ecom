package com.ecom.nabula.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="orderDate")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name="totalPrice")
    private double totalPrice;

    /*
    * a user can have many orders  ( oneToMany )
    * many orders can only have one user ( manyToOne )
    * */
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "order_product",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> products = new ArrayList<>();


}
