package com.ducku.asyncexample.service;

import com.ducku.asyncexample.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderFulfillmentService {
    private final InventoryService inventoryService;
    private final PaymentService paymentService;

    public OrderFulfillmentService(InventoryService inventoryService, PaymentService paymentService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
    }

    /*
       1. Inventory service check order availability
       2. Process payment for order
       3. Notify to the user
       4. Assign to vendor
       5. packaging
       6. assign delivery partner
       7. assign trailer
       8. dispatch product
    * */


    public Order processOrder(Order order) throws InterruptedException {
        if (inventoryService.checkProductAvailability(order.getProductId())) {
            order.setTrackingId(UUID.randomUUID().toString());
            paymentService.processPayment(order);
        } else {
            throw new RuntimeException("Technical exception.Try again later");
        }
        return order;
    }
    @Async("asyncExecutor")
    public void notifyUser(Order order) throws InterruptedException {
        Thread.sleep(4000L);
        log.info("Notified to the user " + Thread.currentThread().getName());
    }

    @Async("asyncExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info("Assign order to vendor " + Thread.currentThread().getName());
    }

    @Async("asyncExecutor")
    public void packaging(Order order) throws InterruptedException {
        Thread.sleep(2000L);
        log.info("Order packaging completed " + order.getProductId());
    }

    @Async("asyncExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Delivery partner assigned" + Thread.currentThread().getName());
    }

    @Async("asyncExecutor")
    public void assignTrailerAndDispatch(Order order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info("Trailer assigned and Order dispatched " + Thread.currentThread().getName());
    }
}
