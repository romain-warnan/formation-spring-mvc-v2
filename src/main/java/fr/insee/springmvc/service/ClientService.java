package fr.insee.springmvc.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insee.springmvc.model.Client;
import fr.insee.springmvc.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	public Client updateWith(Client from, Client to) {
		Objects.requireNonNull(from);
		Objects.requireNonNull(to);
		if(from.getId() != to.getId()) throw new IllegalArgumentException();
		if(to.getTitre() != null) from.setTitre(to.getTitre());
		if(to.getEmail() != null) from.setEmail(to.getEmail());
		if(to.getNom() != null) from.setNom(to.getNom());
		if(to.getDateNaissance() != null) from.setDateNaissance(to.getDateNaissance());
		return from;
	}
	
	public boolean emailDejaUtilise(Client client) {
        return clientRepository.findByEmail(client.getEmail())
            .map(Client::getId)
            .filter(clientId -> !Objects.equals(clientId, client.getId()))
            .isPresent();
    }
}
