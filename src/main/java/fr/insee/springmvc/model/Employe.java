package fr.insee.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "employes")
@DynamicUpdate
@DynamicInsert
public class Employe {

	@Id
	@GeneratedValue(generator = "barGenerator")
	@SequenceGenerator(name = "barGenerator", sequenceName = "seq", allocationSize = 1, initialValue = 100)
	@Column(name = "id")
	private Long id;

	@Column(name = "nom")
	private String nom;

	@JoinColumn(name = "idrole")
	@OneToOne(optional = false)
	private Role role;

	public Long getId() {
		return id;
	} 

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Employe)) {
			return false;
		}
		Employe other = (Employe) object;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
			.add("id= " + id)
			.add("nom=" + nom)
			.add("role=" + role)
			.toString();
	}
}
