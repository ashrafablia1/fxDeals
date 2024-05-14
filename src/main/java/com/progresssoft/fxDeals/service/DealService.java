package com.progresssoft.fxDeals.service;

import com.progresssoft.fxDeals.model.Deal;
import com.progresssoft.fxDeals.repository.DealRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

@Service
public class DealService {

    private final DealRepository repository;

    @Autowired
    public DealService(DealRepository repository) {
        this.repository = repository;
    }


    public Deal SaveDeal(Deal deal) {
        if (!validateDealId(deal.getDealId())) {
            throw new ValidationException("Deal Id already used");
        }
        if (!validateFromCurrency(deal.getFromCurrency())) {
            throw new ValidationException("From Currency not iso");
        }
        if (!validateToCurrency(deal.getToCurrency())) {
            throw new ValidationException("To Currency not iso");
        }
        if (!validateDealTime(deal.getDealTime())) {
            throw new ValidationException("Deal date not valid");
        }
        if (!validateAmount(deal.getAmount())) {
            throw new ValidationException("Amount not valid");
        }
        return repository.save(deal);
    }




    private boolean validateAmount(double amount) {
        return amount > 0.0;
    }

    private boolean validateDealTime(Instant date) {
        return date.isBefore(Instant.now());
    }

    private boolean validateToCurrency(String toCurrency) {
        try{
            Currency.getInstance(toCurrency);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean validateFromCurrency(String fromCurrency) {
       try{
           Currency.getInstance(fromCurrency);
           return true;
       }catch (IllegalArgumentException e){
           return false;
       }
    }

    private boolean validateDealId(UUID uuid) {
        return !repository.existsByDealId(uuid);
    }

}