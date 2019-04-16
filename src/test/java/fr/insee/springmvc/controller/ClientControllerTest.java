package fr.insee.springmvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientControllerTest {

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
	public void client() throws Exception {
		mockMvc.perform(get("/client/102"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("client"))
			.andExpect(view().name("client"))
			.andExpect(forwardedUrl("/WEB-INF/views/client.jsp"));
	}
	
	@Test
	public void client_null() throws Exception {
		mockMvc.perform(get("/client/0"))
		.andExpect(status().isOk())
		.andExpect(model().attributeDoesNotExist("client"))
		.andExpect(view().name("client"))
		.andExpect(forwardedUrl("/WEB-INF/views/client.jsp"));
	}
}