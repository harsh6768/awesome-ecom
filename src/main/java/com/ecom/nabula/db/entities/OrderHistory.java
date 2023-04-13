package com.ecom.nabula.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "status")
    private String status;

    @Column(name = "update_time")
    private Date updateTime;

}
