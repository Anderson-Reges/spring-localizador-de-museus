package com.betrybe.museumfinder.solution;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testCollectionTypeRoute() throws Exception {
    mockMvc.perform(
        get("/collections/count/hist,imag"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.count").value(492))
        .andExpect(jsonPath("$.collectionTypes").isArray())
        .andExpect(jsonPath("$.collectionTypes", hasSize(2)));
  }

  @Test
  void testCollectionTypeSingleTag() throws Exception {
    mockMvc
        .perform(get("/collections/count/hist"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.count").value(387))
        .andExpect(jsonPath("$.collectionTypes").value("hist"));
  }

}
