package com.javatechie.orderservice.service;

import com.javatechie.orderservice.common.Payment;
import com.javatechie.orderservice.common.TransactionRequest;
import com.javatechie.orderservice.common.TransactionResponce;
import com.javatechie.orderservice.entity.Order;
import com.javatechie.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate template;

    public TransactionResponce saveOrder(TransactionRequest request)
    {
        String response="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmmount(order.getPrice());

        //rest api call
         Payment paymentResponce= template.postForObject("http://localhost:9191/payment/doPayment",payment,Payment.class);
         response=paymentResponce.getPaymentStatus().equals("success")
                 ?"Payment Successful and Order Placed"
                 :"Payment Failed and order added to cart";
         repository.save(order);
         return new TransactionResponce(order,paymentResponce.getAmmount(),paymentResponce.getTranasctionId(),response);
    }
}
