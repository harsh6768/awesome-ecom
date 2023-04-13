package com.ecom.nabula.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQueries({
        @NamedQuery(
                name = "Cart.findByCustomerId",
                query = "SELECT c FROM Cart c WHERE c.customer.id = :customerId"
        )
})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        if (this.products.contains(product)) {
            throw new IllegalStateException("Product is already in the cart");
        }
        this.products.add(product);
        product.getCarts().add(this);
    }

}