package fr.insee.springmvc.model;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clients")
@DynamicUpdate
@DynamicInsert
public class Client {

	public Client() {
		titre = Titre.M;
	}

	@Min(value = 0, message = "{client.id.positif}")
	@Id
	@GeneratedValue(generator = "barGenerator")
	@SequenceGenerator(name = "barGenerator", sequenceName = "seq", allocationSize = 1, initialValue = 100)
	@Column(name = "id")
	private Long id;

	@Size(min = 5, max = 50)
	@Column(name = "nom")
	private String nom;

	@Pattern(regexp = "[-_a-z0-9.]+@[-_a-z0-9]+\\.[a-z]{2,4}", message = "{client.email.regex}")
	@Column(name = "email")
	private String email;

	@NotNull(message = "{client.titre.non.vide}")
	@Column(name = "titre")
	@Enumerated(EnumType.ORDINAL)
	private Titre titre;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "{client.date.naissance.non.vide}")
	@Past(message = "{client.date.naissance.passee}")
	@Column(name = "date_naissance")
	private Date dateNaissance;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
	}

	public enum Titre {

		M(1, "Monsieur"), MME(2, "Madame");

		private String libelle;
		private Integer code;

		private Titre(Integer code, String libelle) {
			this.code = code;
			this.libelle = libelle;
		}

		public String getLibelle() {
			return libelle;
		}

		public Integer getCode() {
			return code;
		}

		public static Titre of(Integer titre) {
			switch (titre) {
				case 2:
					return MME;
				default:
					return M;
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Client)) {
			return false;
		}
		Client other = (Client) object;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", this.getClass().getSimpleName() + "[", "]")
			.add("id= " + id)
			.add("nom=" + nom)
			.add("email=" + email)
			.add("dateNaissance=" + dateNaissance)
			.toString();
	}

}
