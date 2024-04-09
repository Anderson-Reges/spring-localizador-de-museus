package com.betrybe.museumfinder.service;

import static com.betrybe.museumfinder.util.CoordinateUtil.isCoordinateValid;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is responsible to make business rule with data.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private final MuseumFakeDatabase database;

  @Autowired
  public MuseumService(MuseumFakeDatabase db) {
    this.database = db;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    Boolean isValid = isCoordinateValid(coordinate);
    if (!isValid) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> optionalMuseum =  database.getClosestMuseum(coordinate, maxDistance);
    return optionalMuseum.orElseThrow(MuseumNotFoundException::new);
  }

  @Override
  public Museum createMuseum(Museum museum) {
    Boolean isValid = isCoordinateValid(museum.getCoordinate());
    if (!isValid) {
      throw new InvalidCoordinateException();
    }

    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return database.getMuseum(id).orElseThrow(MuseumNotFoundException::new);
  }
}
