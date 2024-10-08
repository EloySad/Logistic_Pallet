package com.riwi.logistic_pallet.loads.application;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

import com.riwi.logistic_pallet.loads.domain.LoadRepository;
import com.riwi.logistic_pallet.loads.domain.LoadsEntity;
import com.riwi.logistic_pallet.loads.domain.StatesLoads;
import com.riwi.logistic_pallet.loads.infrastructure.dtos.request.LoadDto;
import com.riwi.logistic_pallet.pallets.domain.PalletEntity;
import com.riwi.logistic_pallet.pallets.domain.PalletRepository;

@Service
public class LoadService {

  private final LoadRepository loadRepository;

  private PalletRepository palletRepository;

  public LoadService(LoadRepository loadRepository) {
    this.loadRepository = loadRepository;
  }

  public LoadsEntity createLoad(LoadDto registerLoadDto) {

    PalletEntity pallet = palletRepository.findById(registerLoadDto.getPalletId())
        .orElseThrow(() -> new RuntimeException("Palet not found"));

    Long pesoActual = loadRepository.findMaxWeight(pallet.getId());

    if (pesoActual + registerLoadDto.getWeight() > pallet.getMaxCapacity()) {
      throw new RuntimeException("The weight of the load exceeds the maximum pallet weight");
    }

    LoadsEntity load = new LoadsEntity();
    load.setWeight(registerLoadDto.getWeight());
    load.setDimension(registerLoadDto.getDimension());
    load.setStates(StatesLoads.PENDING);

    return loadRepository.save(load);
  }

  public Optional<LoadsEntity> getLoadById(Long id) {
    return loadRepository.findById(id);
  }

  public List<LoadsEntity> getAllLoads() {
    return loadRepository.findAll();
  }

  public void deleteLoadById(Long id) {
    loadRepository.deleteById(id);
  }

  public LoadsEntity updateLoad(Long id, LoadDto updateLoadDto) {
    LoadsEntity load = loadRepository.findById(id).get();

    load.setWeight(updateLoadDto.getWeight());
    load.setDimension(updateLoadDto.getDimension());
    load.setStates(StatesLoads.PENDING);

    return loadRepository.save(load);
  }

}