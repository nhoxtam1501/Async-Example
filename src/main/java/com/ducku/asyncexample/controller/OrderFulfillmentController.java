package com.ducku.asyncexample.controller;

import com.ducku.asyncexample.model.Order;
import com.ducku.asyncexample.service.OrderFulfillmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderFulfillmentController {
    private final OrderFulfillmentService fulfillmentService;

    public OrderFulfillmentController(OrderFulfillmentService fulfillmentService) {
        this.fulfillmentService = fulfillmentService;
    }

    @PostMapping
    public ResponseEntity<Order> proccessOrder(@RequestBody Order order) throws InterruptedException {
        fulfillmentService.processOrder(order); //sync

        //async
        fulfillmentService.notifyUser(order);
        fulfillmentService.assignVendor(order);
        fulfillmentService.packaging(order);
        fulfillmentService.assignDeliveryPartner(order);
        fulfillmentService.assignTrailerAndDispatch(order);
        return ResponseEntity.ok(order);
    }
}
