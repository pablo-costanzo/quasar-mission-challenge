package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "satellites")
public class SatelliteEntity implements Serializable {

  @Id
  private String name;
  private double x;
  private double y;

  public SatelliteEntity(String name, double[] position) {
    this.name = name;
    if (position.length == 2) {
      this.x = position[0];
      this.y = position[1];
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

}
