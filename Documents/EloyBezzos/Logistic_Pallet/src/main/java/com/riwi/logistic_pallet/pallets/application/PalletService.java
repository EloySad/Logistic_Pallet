package com.riwi.logistic_pallet.pallets.application;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

import com.riwi.logistic_pallet.pallets.domain.PalletEntity;
import com.riwi.logistic_pallet.pallets.domain.PalletRepository;
import com.riwi.logistic_pallet.pallets.domain.StatesPallets;
import com.riwi.logistic_pallet.pallets.infrastructure.dtos.request.PalletDto;

@Service
public class PalletService {

  private final PalletRepository palletRepository;

  public PalletService(PalletRepository palletRepository) {
    this.palletRepository = palletRepository;
  }

  public PalletEntity createPallet(PalletDto registerPalletDto) {

    PalletEntity pallet = new PalletEntity();
    pallet.setLocation(registerPalletDto.getLocation());
    pallet.setMaxCapacity(registerPalletDto.getMaxCapacity());
    pallet.setStates(StatesPallets.AVAILABLE);

    return palletRepository.save(pallet);
  }

  public Optional<PalletEntity> getPalletById(Long id) {
    return palletRepository.findById(id);
  }

  public List<PalletEntity> getAllPallets() {
    return palletRepository.findAll();
  }

  public void deletePalletById(Long id) {
    palletRepository.deleteById(id);
  }

  public PalletEntity updatePallet(Long id, PalletDto updatePalletDto) {
    PalletEntity pallet = palletRepository.findById(id).get();
    pallet.setLocation(updatePalletDto.getLocation());
    pallet.setMaxCapacity(updatePalletDto.getMaxCapacity());
    return palletRepository.save(pallet);
  }

}