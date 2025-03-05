package desenvweb.tocloc.service;

import desenvweb.tocloc.model.Disponibilidade;
import desenvweb.tocloc.model.Local;
import desenvweb.tocloc.repository.DisponibilidadeRepository;
import desenvweb.tocloc.repository.LocalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository disponibilidadeRepository;
    private final LocalRepository localRepository;

    public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository, LocalRepository localRepository) {
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.localRepository = localRepository;
    }

    @Transactional
    public Disponibilidade create(Disponibilidade disponibilidade) {
        Local local = localRepository.findById(disponibilidade.getLocal().getId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));

        disponibilidade.setLocal(local);
        return disponibilidadeRepository.save(disponibilidade);
    }

    public List<Disponibilidade> findByLocal(Long localId) {
        return disponibilidadeRepository.findByLocalId(localId);
    }
}