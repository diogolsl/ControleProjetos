package trabalhoA2.model;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_projeto;
	private String nome_projeto;
	private Date data_inicio;
	
	
	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;
	

	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	public Long getId_projeto() {
		return id_projeto;
	}
	public void setId_projeto(Long id_projeto) {
		this.id_projeto = id_projeto;
	}
	public String getNome_projeto() {
		return nome_projeto;
	}
	public void setNome_projeto(String nome_projeto) {
		this.nome_projeto = nome_projeto;
	}
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	
	
}
