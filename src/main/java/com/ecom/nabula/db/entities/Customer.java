package com.ecom.nabula.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="customers")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
        @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

}
