package com.ecom.nabula.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name="Cart.findAll",query = "select c from cart c"),
        @NamedQuery(name = "Cart.findOne",query = "select c from Cart c where id=:id")
})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name="totalPrice")
    private double totalPrice;


}
