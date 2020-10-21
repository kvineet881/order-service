package com.javatechie.orderservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Order_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order  implements Serializable {
    @Id
    private  int id;
    private String name;
    private  int qty;
    private  double price;
}
