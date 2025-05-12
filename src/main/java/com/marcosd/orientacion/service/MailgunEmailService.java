package com.marcosd.orientacion.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

@Service
public class MailgunEmailService {

	@Value("${mailgun.api.key}")
	private String apiKey;

	@Value("${mailgun.domain}")
	private String domain;

	@Value("${mailgun.from}")
	private String from;

	public String enviarCorreo() throws UnirestException {

		HttpResponse<JsonNode> request = Unirest
				.post("https://api.mailgun.net/v3/sandbox195e07633bf1425a8f5458dc51856711.mailgun.org/messages")
				.basicAuth("api", apiKey)
				.queryString("from", "Mailgun Sandbox <postmaster@sandbox195e07633bf1425a8f5458dc51856711.mailgun.org>")
				.queryString("to", "MARCOS FERNANDEZ <marcosd@educastur.org>")
				.queryString("subject", "Hello MARCOS FERNANDEZ")
				.queryString("text",
						"Congratulations MARCOS FERNANDEZ, you just sent an email with Mailgun! You are truly awesome!")
				.asJson();

		return request.getBody().toString();

	}

	/*
	 * public String enviarCorreo(String destinatario, String asunto, String
	 * mensaje) throws UnirestException { HttpResponse<JsonNode> response =
	 * Unirest.post("https://api.mailgun.net/v3/" + domain + "/messages")
	 * .basicAuth("api", apiKey) .queryString("from", from) .queryString("to",
	 * destinatario) .queryString("subject", asunto) .queryString("text", mensaje)
	 * .asJson();
	 * 
	 * if (response.getStatus() != 200) { return "Error al enviar el email: " +
	 * response.getStatusText(); //throw new
	 * RuntimeException("Error al enviar el email: " + response.getStatusText()); }
	 * else return response.getStatusText(); }
	 */
}
