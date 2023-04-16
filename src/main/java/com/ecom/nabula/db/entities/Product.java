package com.ecom.nabula.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name="Product.fineOne",query="Select p from Product p where p.id= :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="desc")
    private String desc;

    @Column(name="price")
    private long price;

}
