package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories;

import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.SatelliteInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteInfoRepository extends JpaRepository<SatelliteInfoEntity, String> {

}
