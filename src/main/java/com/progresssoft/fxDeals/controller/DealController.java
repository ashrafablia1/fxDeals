package com.progresssoft.fxDeals.controller;

import com.progresssoft.fxDeals.model.Deal;
import com.progresssoft.fxDeals.service.DealService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DealController {

    private final DealService service;
    Logger logger = LoggerFactory.getLogger(DealController.class);


    @Autowired
    public DealController(DealService service) {
        this.service = service;
    }

    @PostMapping("/create/deal")
    public ResponseEntity<String> CreateDeal(@Valid @RequestBody Deal deal) {
        service.SaveDeal(deal);
        logger.info("Deal Created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body("Deal created successfully");
    }

}
