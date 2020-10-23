package com.javatechie.orderservice.service;

import com.javatechie.orderservice.common.Payment;
import com.javatechie.orderservice.common.TransactionRequest;
import com.javatechie.orderservice.common.TransactionResponce;
import com.javatechie.orderservice.entity.Order;
import com.javatechie.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class  OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate template;
    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL; //  "http://PAYMENT-SERVICE/payment/doPayment"


    public TransactionResponce saveOrder(TransactionRequest request)
    {
        String response="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmmount(order.getPrice());


         Payment paymentResponce= template.postForObject(ENDPOINT_URL,payment,Payment.class);
         response=paymentResponce.getPaymentStatus().equals("success")
                 ?"Payment Successful and Order Placed"
                 :"Payment Failed and order added to cart";
         repository.save(order);
         return new TransactionResponce(order,paymentResponce.getAmmount(),paymentResponce.getTranasctionId(),response);
    }
}
