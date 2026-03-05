package trabalhoA2.model;

import java.util.*;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjeto;
	private String nomeProjeto;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;


	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;
	

	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	public Long getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(Long idProjeto) {
		this.idProjeto = idProjeto;
	}
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	
}
