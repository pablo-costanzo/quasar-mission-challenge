package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "satellites_info")
public class SatelliteInfoEntity implements Serializable {

  @Id
  private String name;

  private double distance;

  @ElementCollection
  private List<String> message;

  public String getName() {
    return name;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public List<String> getMessage() {
    return message;
  }
}
