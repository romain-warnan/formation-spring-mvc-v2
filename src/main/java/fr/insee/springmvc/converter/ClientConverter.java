package fr.insee.springmvc.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.insee.springmvc.model.Client;
import fr.insee.springmvc.repository.ClientRepository;

@Component
public class ClientConverter implements Converter<String, Client> {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client convert(String id) {
		Optional<Client> client = clientRepository.findById(Long.valueOf(id));
		return client.orElse(null);
	}
}
