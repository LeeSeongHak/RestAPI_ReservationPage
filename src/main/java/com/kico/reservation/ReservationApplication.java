package com.kico.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author イーソンハク
 * @version 1.0
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.kico.reservation")
@EntityScan(basePackages = "com.kico.reservation.model")
@EnableJpaRepositories("com.kico.reservation.model")
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}
}