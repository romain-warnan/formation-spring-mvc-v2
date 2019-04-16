package fr.insee.springmvc.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void whenCount_returnSize() {
		assertThat(clientRepository.count()).isEqualTo(10L);
	}

	@Test
	public void whenFindAll_returnAll() {
		assertThat(clientRepository.findAll()).hasSize(10);
	}

	@Test
	public void whenFindById_returnOne() {
		assertThat(clientRepository.findById(102L)).isPresent();
	}

	@Test
	public void whenFindById_returnEmpty() {
		assertThat(clientRepository.findById(0L)).isEmpty();
	}

	@Test
	public void whenSearchByEmail_returnOne() {
		assertThat(clientRepository.findByEmail("ksims3@usatoday.com")).isPresent();
	}
}
