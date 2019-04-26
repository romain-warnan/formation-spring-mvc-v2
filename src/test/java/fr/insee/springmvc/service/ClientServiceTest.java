package fr.insee.springmvc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.insee.springmvc.model.Client;
import fr.insee.springmvc.model.Client.Titre;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private ClientService clientService;

	private Client fromA, toA, other;
	
	@Before
	public void before() {
		fromA = fromAClient();
		toA = toAClient();
		other = otherClient();
	}
	
	@Test
	public void whenUpdateNullClient_throwException() {
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> clientService.updateWith(null, other));
	}
	
	@Test
	public void whenUpdateFromNullClient_throwException() {
		assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> clientService.updateWith(other, null));
	}
	
	@Test
	public void whenUpdateFromDifferentClient_throwException() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> clientService.updateWith(fromA, other));
	}
	
	@Test
	public void whenUpdate_fromA_toA_returnModifiedClient() {
		assertThat(clientService.updateWith(fromA, toA))
			.hasFieldOrPropertyWithValue("nom", toA.getNom())
			.hasFieldOrPropertyWithValue("email", toA.getEmail())
			.hasFieldOrPropertyWithValue("titre", toA.getTitre())
			.hasFieldOrPropertyWithValue("dateNaissance", fromA.getDateNaissance());
	}
	
	@Test
	public void whenUpdate_toA_fromA_returnModifiedClient() {
		assertThat(clientService.updateWith(toA, fromA))
		.hasFieldOrPropertyWithValue("nom", fromA.getNom())
		.hasFieldOrPropertyWithValue("email", fromA.getEmail())
		.hasFieldOrPropertyWithValue("titre", fromA.getTitre())
		.hasFieldOrPropertyWithValue("dateNaissance", fromA.getDateNaissance());
	}

	private Client fromAClient() {
		Client client = new Client();
		client.setId(1L);
		client.setDateNaissance(Date.from((LocalDate.of(1984, 4, 21).atStartOfDay(ZoneId.systemDefault()).toInstant())));
		client.setNom("nom_a");
		client.setEmail("email_a");
		client.setTitre(Titre.M);
		return client;
	}
	
	private Client toAClient() {
		Client client = new Client();
		client.setId(1L);
		client.setNom("nom_b");
		client.setEmail("email_b");
		client.setTitre(Titre.MME);
		return client;
	}
	
	private Client otherClient() {
		Client client = new Client();
		client.setId(2L);
		client.setEmail("email_c");
		return client;
	}
}
