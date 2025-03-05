package desenvweb.tocloc.service;


import desenvweb.tocloc.model.Local;
import desenvweb.tocloc.model.User;
import desenvweb.tocloc.repository.LocalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocalService {
    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    @Transactional
    public Local create(Local local) {
        return localRepository.save(local);
    }

    @Transactional
    public List<Local> findAll() {
        return localRepository.findAll();
    }

    @Transactional
    public Local findById(Long id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Local não encontrado!"));
    }

    @Transactional
    public Local update(Long id, Local localDetails) {
        Local local = findById(id);
        local.setNome(localDetails.getNome());
        local.setEndereco(localDetails.getEndereco());
        local.setDescricao(localDetails.getDescricao());
        local.setOwner(localDetails.getOwner());
        return localRepository.save(local);
    }

    @Transactional
    public void delete(Long id) {
        localRepository.deleteById(id);
    }
}