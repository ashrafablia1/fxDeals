package com.progresssoft.fxDeals.repository;

import com.progresssoft.fxDeals.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    boolean existsByDealId(UUID dealId);

}
