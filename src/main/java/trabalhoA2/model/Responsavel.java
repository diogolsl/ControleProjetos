package trabalhoA2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Responsavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idResponsavel;
	private String nome;
	private String email;
	
	public Long getIdResponsavel() {
		return idResponsavel;
	}
	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
