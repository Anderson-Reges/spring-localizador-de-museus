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

@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Museum> getMuseum(@PathVariable Long id) {
    Museum museum = service.getMuseum(id);
    return ResponseEntity.ok(museum);
  }

  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam String lat,
      @RequestParam String lng,
      @RequestParam String max_dist_km
  ) {
    double latD = Double.parseDouble(lat);
    double lngD = Double.parseDouble(lng);
    double maxD = Double.parseDouble(max_dist_km);
    Coordinate coor = new Coordinate(latD, lngD);
    Museum museum = service.getClosestMuseum(coor, maxD);
    return ResponseEntity.ok(modelToDto(museum));
  }

  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumDto) {
    Museum museum = service.createMuseum(dtoToModel(museumDto));
    return ResponseEntity.status(HttpStatus.CREATED).body(museum);
  }
}
