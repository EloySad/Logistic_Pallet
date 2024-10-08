package com.riwi.logistic_pallet.loads.domain;

import com.riwi.logistic_pallet.common.domain.AuditableEntity;
import com.riwi.logistic_pallet.pallets.domain.PalletEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity(name = "loads")
public class LoadsEntity extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long weight;

  @Column(nullable = false, unique = true)
  private String dimension;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatesLoads states;

  @ManyToOne
  @JoinColumn(name = "pallet_id")
  private PalletEntity pallet;

}
