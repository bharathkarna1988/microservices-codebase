package com.techmaster.microservice.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techmaster.microservice.currencyexchangeservice.bean.CurrencyExchange;
import com.techmaster.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExcangeValue(@PathVariable String from, @PathVariable String to) {
		/*
		 * CurrencyExchange currencyExchange = new CurrencyExchange(100, "USD", "INR",
		 * BigDecimal.valueOf(60));
		 */
		CurrencyExchange currencyExchange =currencyExchangeRepository.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new RuntimeException("Unable to find for "+from+" to "+to);
		}
		
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
		return currencyExchange;

	}

}
