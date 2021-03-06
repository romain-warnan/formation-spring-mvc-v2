package fr.insee.springmvc.model;

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import org.keycloak.representations.AccessToken;

public class Utilisateur {

	private String nom, email, idep;
	private Set<String> roles;
	
	public Utilisateur() {}
	
	public static Utilisateur fromToken(AccessToken token) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.nom = token.getName();
		utilisateur.idep = token.getPreferredUsername();
		utilisateur.email = token.getEmail();
		utilisateur.roles = token.getRealmAccess().getRoles();
		return utilisateur;
	}
	
	public static Utilisateur defaultUser() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.nom = "Default User";
		utilisateur.idep = "idep";
		utilisateur.email = "default.user@insee.fr";
		utilisateur.roles = Set.of("spring_mvc");
		return utilisateur;
	}

	public String getNom() {
		return nom;
	}

	public String getEmail() {
		return email;
	}

	public String getIdep() {
		return idep;
	}

	public Set<String> getRoles() {
		return roles;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.idep);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Utilisateur)) {
			return false;
		}
		Utilisateur other = (Utilisateur) object;
		return Objects.equals(this.idep, other.idep);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
			.add("idep=" + idep)
			.add("nom=" + nom)
			.toString();
	}
}
