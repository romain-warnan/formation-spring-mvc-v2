package fr.insee.springmvc.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import fr.insee.springmvc.model.Client;

@Service
public class ClientService {

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
}
