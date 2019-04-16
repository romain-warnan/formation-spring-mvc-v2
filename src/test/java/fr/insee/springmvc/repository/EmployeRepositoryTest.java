package fr.insee.springmvc.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeRepositoryTest {

	@Autowired
	private EmployeRepository employeRepository;

	@Test
	public void whenCount_returnSize() {
		assertThat(employeRepository.count()).isEqualTo(3L);
	}

	@Test
	public void whenFindAll_returnAll() {
		assertThat(employeRepository.findAll()).hasSize(3);
	}

	@Test
	public void whenFindById_returnOne() {
		assertThat(employeRepository.findById(1L)).isPresent();
	}

	@Test
	public void whenFindById_returnEmpty() {
		assertThat(employeRepository.findById(0L)).isEmpty();
	}
}
