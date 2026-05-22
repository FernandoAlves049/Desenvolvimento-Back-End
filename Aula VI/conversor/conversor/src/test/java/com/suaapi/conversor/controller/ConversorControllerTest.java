package com.suaapi.conversor.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConversorControllerTest {

	@Value("${local.server.port}")
	private int port;

	private final HttpClient httpClient = HttpClient.newHttpClient();

	@Test
	void shouldConvertCelsiusToKelvin() throws Exception {
		HttpResponse<String> response = sendGet("/conversor/celsiusParaKelvin/0");

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.body()).isEqualTo("273.15");
	}

	@Test
	void shouldReturnBadRequestForNonNumericValue() throws Exception {
		HttpResponse<String> response = sendGet("/conversor/celsiusParaKelvin/abc");

		assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		assertThat(response.body()).contains("O valor informado não é numérico.");
	}

	private HttpResponse<String> sendGet(String path) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder(URI.create(url(path))).GET().build();
		return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	}

	private String url(String path) {
		return "http://localhost:" + port + path;
	}
}