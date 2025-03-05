package desenvweb.tocloc.repository;

import desenvweb.tocloc.model.Disponibilidade;
import desenvweb.tocloc.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    List<Disponibilidade> findByLocalId(Long localId);
}
