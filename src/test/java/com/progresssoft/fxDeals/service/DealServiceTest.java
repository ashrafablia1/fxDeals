package com.progresssoft.fxDeals.service;

import com.progresssoft.fxDeals.model.Deal;
import com.progresssoft.fxDeals.repository.DealRepository;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DealServiceTest {

    @Mock
    private DealRepository repository;

    @InjectMocks
    private DealService service;


    @Test
    void saveDeal_success() {
        Deal newDeal = new Deal();
        newDeal.setDealId(UUID.randomUUID());
        newDeal.setFromCurrency("EUR");
        newDeal.setToCurrency("USD");
        newDeal.setDealTime(Instant.now());
        newDeal.setAmount(85.36);

        Mockito.when(repository.existsByDealId(newDeal.getDealId())).thenReturn(false);
        service.SaveDeal(newDeal);
        Mockito.verify(repository).save(newDeal);
    }

    @Test
    void save_fail_DuplicateDealId(){
        Deal newDeal = new Deal();
        newDeal.setDealId(UUID.randomUUID());
        newDeal.setFromCurrency("EUR");
        newDeal.setToCurrency("USD");
        newDeal.setDealTime(Instant.now());
        newDeal.setAmount(85.36);

        Mockito.when(repository.existsByDealId(newDeal.getDealId())).thenReturn(true);
       try {
           service.SaveDeal(newDeal);
       } catch (Exception e){
           assertEquals("Deal Id already used", e.getMessage());
       }
    }


    @Test
    void save_fail_InvalidFromCurrency(){
        Deal newDeal = new Deal();
        newDeal.setDealId(UUID.randomUUID());
        newDeal.setFromCurrency("abq");
        newDeal.setToCurrency("USD");
        newDeal.setDealTime(Instant.now());
        newDeal.setAmount(85.36);

        Mockito.when(repository.existsByDealId(newDeal.getDealId())).thenReturn(false);
        try {
            service.SaveDeal(newDeal);
        } catch (Exception e){
            assertEquals("From Currency not iso", e.getMessage());
        }
    }

    @Test
    void save_fail_InvalidToCurrency(){
        Deal newDeal = new Deal();
        newDeal.setDealId(UUID.randomUUID());
        newDeal.setFromCurrency("EUR");
        newDeal.setToCurrency("ABS");
        newDeal.setDealTime(Instant.now());
        newDeal.setAmount(85.36);

        Mockito.when(repository.existsByDealId(newDeal.getDealId())).thenReturn(false);
        try {
            service.SaveDeal(newDeal);
        } catch (Exception e){
            assertEquals("To Currency not iso", e.getMessage());
        }
    }

    @Test
    void save_fail_DealTimeINFuture(){
        Deal newDeal = new Deal();
        newDeal.setDealId(UUID.randomUUID());
        newDeal.setFromCurrency("EUR");
        newDeal.setToCurrency("USD");
        newDeal.setDealTime(Instant.now().plusMillis(10000));
        newDeal.setAmount(85.36);

        Mockito.when(repository.existsByDealId(newDeal.getDealId())).thenReturn(false);
        try {
            service.SaveDeal(newDeal);
        } catch (Exception e){
            assertEquals("Deal date not valid", e.getMessage());
        }
    }



    @Test
    void save_fail_InvalidAmount(){
        Deal newDeal = new Deal();
        newDeal.setDealId(UUID.randomUUID());
        newDeal.setFromCurrency("EUR");
        newDeal.setToCurrency("USD");
        newDeal.setDealTime(Instant.now());
        newDeal.setAmount(0.0);

        Mockito.when(repository.existsByDealId(newDeal.getDealId())).thenReturn(false);
        try {
            service.SaveDeal(newDeal);
        } catch (Exception e){
            assertEquals("Amount not valid", e.getMessage());
        }
    }



}