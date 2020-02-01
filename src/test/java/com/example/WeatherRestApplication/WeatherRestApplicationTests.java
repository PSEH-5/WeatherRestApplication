package com.example.WeatherRestApplication;

import com.example.WeatherRestApplication.resource.WeatherResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WeatherRestApplicationTests {

	@MockBean
	private WeatherResource weatherResource;

	@Autowired
	private MockMvc mockMvc;



	@Test
	public void contextLoads() {
	}

	@Test
	public void find_weatherByCity_OK() throws Exception {

		mockMvc.perform(get("/api/cities/Tokyo"))
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
//				.andExpect(jsonPath("$.id", is(1)))
//				.andExpect(jsonPath("$.name", is("Book Name")))
//				.andExpect(jsonPath("$.author", is("Mkyong")))
//				.andExpect(jsonPath("$.price", is(9.99)));

		verify(weatherResource, times(1)).getTemperature("Tokyo");

	}

}
