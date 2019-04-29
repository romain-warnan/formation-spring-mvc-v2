package fr.insee.springmvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.insee.springmvc.model.Client;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModificationClientControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders
			.webAppContextSetup(this.webApplicationContext)
			.build();
	}

	@Test
	public void afficherModificationClient() throws Exception {
		mockMvc.perform(get("/client/106/modification"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("client"))
			.andExpect(model().attribute("client", isA(Client.class)))
			.andExpect(model().attribute("client", hasProperty("id", equalTo(106L))))
			.andExpect(view().name("modification-client"))
			.andExpect(forwardedUrl("/WEB-INF/views/modification-client.jsp"));
	}
	
	@Test
	public void enregistrerModificationClient() throws Exception {
		mockMvc.perform(post("/client/106/modification")
				.param("id", "106")
				.param("nom", "to_nom")
				.param("email", "email@valid.fr")
				.param("titre", "M")
				.param("dateNaissance", "21/04/1984"))
			.andExpect(status().isFound())
			.andExpect(flash().attributeExists("modification"))
			.andExpect(flash().attribute("modification", true))
			.andExpect(redirectedUrl("/client/106"));
	}

	@Test
	public void erreurNomTropCourtEmailInvalideDateInvalide() throws Exception {
		mockMvc.perform(post("/client/106/modification")
				.param("id", "106")
				.param("nom", "az")
				.param("email", "email")
				.param("titre", "MME")
				.param("dateNaissance", "azerty"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(model().errorCount(3))
			.andExpect(model().attributeHasFieldErrors("client", "nom", "email", "dateNaissance"))
            .andExpect(forwardedUrl("/WEB-INF/views/modification-client.jsp"));
	}

	@Test
	public void erreurEmailDejaUtiliseDateFuture() throws Exception {
		mockMvc.perform(post("/client/106/modification")
				.param("id", "106")
				.param("nom", "nom valide")
				.param("email", "pjohnson4@ovh.net")
				.param("titre", "M")
				.param("dateNaissance", "10/11/2199"))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors())
			.andExpect(model().errorCount(2))
            .andExpect(forwardedUrl("/WEB-INF/views/modification-client.jsp"));
	}
}