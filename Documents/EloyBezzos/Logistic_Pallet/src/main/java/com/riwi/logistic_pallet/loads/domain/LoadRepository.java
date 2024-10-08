package com.riwi.logistic_pallet.loads.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadRepository extends JpaRepository<LoadsEntity, Long> {
  @Query("SELECT SUM(l.weight) FROM loads l WHERE l.pallet.id = :palletId")
  Long findMaxWeight(@Param("palletId") Long palletId);
}