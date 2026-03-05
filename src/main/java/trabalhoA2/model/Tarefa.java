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
	private Long idTarefa;
	private String descricao;
	private String statusTarefa;
	
	@ManyToOne
	@JoinColumn(name = "id_projeto")
	private Projeto projeto;
	  
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public Long getIdTarefa() {
		return idTarefa;
	}
	public void setIdTarefa(Long idTarefa) {
		this.idTarefa = idTarefa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatusTarefa() {
		return statusTarefa;
	}
	public void setStatusTarefa(String statusTarefa) {
		this.statusTarefa = statusTarefa;
	}
	
}