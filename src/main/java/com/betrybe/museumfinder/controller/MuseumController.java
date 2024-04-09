package com.betrybe.museumfinder.controller;

import static com.betrybe.museumfinder.util.ModelDtoConverter.dtoToModel;
import static com.betrybe.museumfinder.util.ModelDtoConverter.modelToDto;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is responsible for API routes.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * This method retrieve a Museum by id.
   *
   * @param id Museum id
   * @return returns a Museum
   */
  @GetMapping("/{id}")
  public ResponseEntity<Museum> getMuseum(@PathVariable Long id) {
    Museum museum = service.getMuseum(id);
    return ResponseEntity.ok(museum);
  }

  /**
   * This method get the closest Museum.
   *
   * @param lat latitude query
   * @param lng longitude query
   * @param maxDistKm max distance in Km query
   * @return returns a Museum DTO
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam String lat,
      @RequestParam String lng,
      @RequestParam(name = "max_dist_km") String maxDistKm
  ) {
    double latD = Double.parseDouble(lat);
    double lngD = Double.parseDouble(lng);
    double maxD = Double.parseDouble(maxDistKm);
    Coordinate coor = new Coordinate(latD, lngD);
    Museum museum = service.getClosestMuseum(coor, maxD);
    return ResponseEntity.ok(modelToDto(museum));
  }

  /**
   * This method create a Museum.
   *
   * @param museumDto Museum DTO body
   * @return returns a Museum
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumDto) {
    Museum museum = service.createMuseum(dtoToModel(museumDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(museum);
  }
}
