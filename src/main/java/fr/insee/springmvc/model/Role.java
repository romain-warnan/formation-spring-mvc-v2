package fr.insee.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "roles")
@DynamicUpdate
@DynamicInsert
public class Role {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "libelle")
	private String libelle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Role)) {
			return false;
		}
		Role other = (Role) object;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
			.add("id= " + id)
			.add("libelle=" + libelle)
			.toString();
	}
}
