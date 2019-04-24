package fr.insee.springmvc.controller;

import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.insee.springmvc.model.Utilisateur;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilisateurControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MockHttpSession session;

	private MockMvc mockMvc;

	@Before
	public void before() {
		this.mockMvc = MockMvcBuilders
			.webAppContextSetup(this.webApplicationContext)
			.build();
		session.setAttribute("utilisateur", new Utilisateur());
	}

	@Test
	public void utilisateur() throws Exception {
		mockMvc.perform(get("/utilisateur").session(session))
			.andExpect(status().isOk())
			.andExpect(request().sessionAttribute("utilisateur", isA(Utilisateur.class)))
			.andExpect(model().attributeExists("utilisateur"))
			.andExpect(model().attribute("utilisateur", isA(Utilisateur.class)))
			.andExpect(view().name("utilisateur"))
			.andExpect(forwardedUrl("/WEB-INF/views/utilisateur.jsp"));
	}
}