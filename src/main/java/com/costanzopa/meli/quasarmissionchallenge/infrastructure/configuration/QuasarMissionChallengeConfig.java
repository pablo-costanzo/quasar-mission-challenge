package com.costanzopa.meli.quasarmissionchallenge.infrastructure.configuration;

import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl.CreateSatelliteInfoUseCaseImpl;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl.GetAllSatelliteInfoUseCaseImpl;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl.GetAllSatellitesUseCaseImpl;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl.GetMessageDecodeUseCaseImpl;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl.GetSourceLocationUseCaseImpl;
import com.costanzopa.meli.quasarmissionchallenge.core.model.decode.RebelDecoderMethod;
import com.costanzopa.meli.quasarmissionchallenge.core.model.location.TrilaterationLocationMethodImp;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl.PositionRestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl.SatelliteInfoRestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl.SatelliteRestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.impl.SatelliteInfoRepositoryConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.impl.SatelliteRepositoryConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.impl.SatelliteInfoServiceRepositoryImpl;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.impl.SatelliteServiceRepositoryImpl;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories.SatelliteInfoRepository;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories.SatelliteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuasarMissionChallengeConfig {

  private final SatelliteInfoRepository satelliteInfoRepository;
  private final SatelliteRepository satelliteRepository;

  public QuasarMissionChallengeConfig(
      SatelliteInfoRepository satelliteInfoRepository,
      SatelliteRepository satelliteRepository) {
    this.satelliteInfoRepository = satelliteInfoRepository;
    this.satelliteRepository = satelliteRepository;
  }


  @Bean
  public SatelliteRepositoryConverter createSatelliteRepositoryConverter() {
    return new SatelliteRepositoryConverter();
  }

  @Bean
  public SatelliteInfoRepositoryConverter createSatelliteInfoRepositoryConverter() {
    return new SatelliteInfoRepositoryConverter();
  }


  @Bean
  public SatelliteInfoServiceRepositoryImpl createSatelliteInfoServiceRepositoryImpl() {
    return new SatelliteInfoServiceRepositoryImpl(satelliteInfoRepository,
        createSatelliteInfoRepositoryConverter());
  }


  @Bean
  public SatelliteServiceRepositoryImpl createSatelliteServiceRepositoryImpl() {
    return new SatelliteServiceRepositoryImpl(satelliteRepository,
        createSatelliteRepositoryConverter());
  }


  @Bean
  public PositionRestConverter createPositionRestConverter() {
    return new PositionRestConverter();
  }

  @Bean
  public SatelliteInfoRestConverter createSatelliteInfoRestConverter() {
    return new SatelliteInfoRestConverter();
  }

  @Bean
  public SatelliteRestConverter createSatelliteRestConverter() {
    return new SatelliteRestConverter(createPositionRestConverter());
  }

  @Bean
  public SatelliteInfoRest createSatelliteInfoRest() {
    return new SatelliteInfoRest();
  }


  @Bean
  public TrilaterationLocationMethodImp createTrilaterationLocationMethodImp() {
    return new TrilaterationLocationMethodImp();
  }

  @Bean
  public RebelDecoderMethod createRebelDecoderMethod() {
    return new RebelDecoderMethod();
  }

  @Bean
  public GetSourceLocationUseCaseImpl createGetAllCategoriesUseCase() {
    return new GetSourceLocationUseCaseImpl(createTrilaterationLocationMethodImp(),
        createSatelliteServiceRepositoryImpl());
  }

  @Bean
  public GetMessageDecodeUseCaseImpl createCreateCategoryUseCase() {
    return new GetMessageDecodeUseCaseImpl(createRebelDecoderMethod());
  }


  @Bean
  public GetAllSatellitesUseCaseImpl createGetAllSatellitesUseCaseImp() {
    return new GetAllSatellitesUseCaseImpl(createSatelliteServiceRepositoryImpl());
  }

  @Bean
  public CreateSatelliteInfoUseCaseImpl createCreateSatelliteInfoUseCase() {
    return new CreateSatelliteInfoUseCaseImpl(createSatelliteServiceRepositoryImpl(),
        createSatelliteInfoServiceRepositoryImpl());
  }

  @Bean
  public GetAllSatelliteInfoUseCaseImpl createGetAllSatelliteInfoUseCaseImpl() {
    return new GetAllSatelliteInfoUseCaseImpl(
        createSatelliteInfoServiceRepositoryImpl());
  }
}
