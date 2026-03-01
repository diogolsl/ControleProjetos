package trabalhoA2.repository;

import trabalhoA2.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
