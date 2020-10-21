package com.javatechie.orderservice.common;

import com.javatechie.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponce {
    private Order order;
    private double ammount;
    private String transactionId;
    private String message;
}
