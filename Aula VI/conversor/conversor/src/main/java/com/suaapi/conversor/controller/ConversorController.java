package com.suaapi.conversor.controller;

import com.suaapi.conversor.NumberConverter;
import com.suaapi.conversor.TemperatureOperator;
import com.suaapi.conversor.exception.UnsupportedTemperatureTargetException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversor")
public class ConversorController {

	private final TemperatureOperator temperatureOperator;

	public ConversorController(TemperatureOperator temperatureOperator) {
		this.temperatureOperator = temperatureOperator;
	}

	@GetMapping("/celsiusParaKelvin/{value}")
	public ResponseEntity<Double> celsiusParaKelvin(@PathVariable String value) {
		return ResponseEntity.ok(convert(value, temperatureOperator::celsiusToKelvin));
	}

	@GetMapping("/celsiusParaFahrenheit/{value}")
	public ResponseEntity<Double> celsiusParaFahrenheit(@PathVariable String value) {
		return ResponseEntity.ok(convert(value, temperatureOperator::celsiusToFahrenheit));
	}

	@GetMapping("/kelvinParaCelsius/{value}")
	public ResponseEntity<Double> kelvinParaCelsius(@PathVariable String value) {
		return ResponseEntity.ok(convert(value, temperatureOperator::kelvinToCelsius));
	}

	@GetMapping("/kelvinParaFahrenheit/{value}")
	public ResponseEntity<Double> kelvinParaFahrenheit(@PathVariable String value) {
		return ResponseEntity.ok(convert(value, temperatureOperator::kelvinToFahrenheit));
	}

	@GetMapping("/fahrenheitParaCelsius/{value}")
	public ResponseEntity<Double> fahrenheitParaCelsius(@PathVariable String value) {
		return ResponseEntity.ok(convert(value, temperatureOperator::fahrenheitToCelsius));
	}

	@GetMapping("/fahrenheitParaKelvin/{value}")
	public ResponseEntity<Double> fahrenheitParaKelvin(@PathVariable String value) {
		return ResponseEntity.ok(convert(value, temperatureOperator::fahrenheitToKelvin));
	}

	private double convert(String value, TemperatureConversion conversion) {
		if (!NumberConverter.isNumeric(value)) {
			throw new UnsupportedTemperatureTargetException("O valor informado não é numérico.", value);
		}

		return conversion.apply(Double.parseDouble(value.replace(',', '.')));
	}

	@FunctionalInterface
	private interface TemperatureConversion {
		double apply(double value);
	}
}