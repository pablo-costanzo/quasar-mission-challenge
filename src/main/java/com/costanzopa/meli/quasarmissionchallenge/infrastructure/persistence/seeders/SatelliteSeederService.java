package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.seeders;

import com.costanzopa.meli.quasarmissionchallenge.core.model.types.SatelliteNameType;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.common.PersistenceConstants;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.SatelliteEntity;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.builder.SatelliteEntityBuilder;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories.SatelliteInfoRepository;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories.SatelliteRepository;
import java.util.Arrays;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SatelliteSeederService implements CommandLineRunner {

  private final SatelliteRepository satelliteRepository;
  private final SatelliteInfoRepository satelliteInfoRepository;

  private void dropDatabase() {
    satelliteRepository.deleteAll();
    satelliteInfoRepository.deleteAll();
  }

  private void seedDatabase() {
    SatelliteEntityBuilder satelliteEntityBuilder = new SatelliteEntityBuilder();
    Collection<SatelliteEntity> repository = Arrays.asList(
        satelliteEntityBuilder.name(SatelliteNameType.KENOBI.toString())
            .x(PersistenceConstants.KENOBI_X_POSITION).y(PersistenceConstants.KENOBI_Y_POSITION)
            .build(),
        satelliteEntityBuilder.name(SatelliteNameType.SATO.toString()).x(PersistenceConstants.SATO_X_POSITION)
            .y(PersistenceConstants.SATO_Y_POSITION).build(),
        satelliteEntityBuilder.name(SatelliteNameType.SKYWALKER.toString())
            .x(PersistenceConstants.SKYWALKER_X_POSITION).y(PersistenceConstants.SKYWALKER_Y_POSITION)
            .build());
    satelliteRepository.saveAll(repository);
  }

  @Override
  public void run(String... args) throws Exception {
    dropDatabase();
    seedDatabase();
  }
}
