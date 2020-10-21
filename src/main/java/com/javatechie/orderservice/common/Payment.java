package com.javatechie.orderservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private int paymentId;
    private String paymentStatus;
    private String  tranasctionId;
    private  int orderId;
    private double ammount;
}
