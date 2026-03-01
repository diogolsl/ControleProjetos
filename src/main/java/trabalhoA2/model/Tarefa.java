package trabalhoA2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tarefa;
	private String descricao;
	private String status_tarefa;
	
	@ManyToOne
	@JoinColumn(name = "id_projeto")
	private Projeto projeto;
	  
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public Long getId_tarefa() {
		return id_tarefa;
	}
	public void setId_tarefa(Long id_tarefa) {
		this.id_tarefa = id_tarefa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus_tarefa() {
		return status_tarefa;
	}
	public void setStatus_tarefa(String status_tarefa) {
		this.status_tarefa = status_tarefa;
	}
	
}