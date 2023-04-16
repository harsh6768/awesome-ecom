package com.ecom.nabula.db.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders")
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
    @JoinColumn(name="user_id")
    private User user;


}
