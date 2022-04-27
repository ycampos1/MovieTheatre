package com.codedifferently.MovieApiV3;

import com.codedifferently.MovieApiV3.domain.cinema.components.models.HallRow;
import com.codedifferently.MovieApiV3.domain.cinema.components.services.HallRowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MovieApiV3Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieApiV3Application.class, args);
	}

	@Autowired
	private HallRowService hallRowService;

	@Override
	public void run(String... args) throws Exception {
		HallRow row = hallRowService.create(new HallRow("A"));
		HallRow row3 = hallRowService.create(new HallRow("B"));

	}
}
