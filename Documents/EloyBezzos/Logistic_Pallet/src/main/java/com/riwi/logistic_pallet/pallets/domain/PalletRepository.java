package com.riwi.logistic_pallet.pallets.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletRepository extends JpaRepository<PalletEntity, Long> {
}