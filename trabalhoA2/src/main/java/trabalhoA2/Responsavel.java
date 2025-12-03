package trabalhoA2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Responsavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_responsavel;
	private String nome;
	private String email;
	
	public Long getId_responsavel() {
		return id_responsavel;
	}
	public void setId_responsavel(Long id_responsavel) {
		this.id_responsavel = id_responsavel;
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
