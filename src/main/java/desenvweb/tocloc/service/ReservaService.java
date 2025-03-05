package desenvweb.tocloc.service;

import desenvweb.tocloc.model.Disponibilidade;
import desenvweb.tocloc.model.Reserva;
import desenvweb.tocloc.model.User;
import desenvweb.tocloc.repository.DisponibilidadeRepository;
import desenvweb.tocloc.repository.ReservaRepository;
import desenvweb.tocloc.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final UserRepository userRepository;

    public ReservaService(ReservaRepository reservaRepository, DisponibilidadeRepository disponibilidadeRepository,
                          UserRepository userRepository) {
        this.reservaRepository = reservaRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Reserva create(Reserva reserva) {
        User usuario = userRepository.findById(reserva.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Disponibilidade disponibilidade = disponibilidadeRepository.findById(reserva.getDisponibilidade().getId())
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada"));

        if(!"AVAILABLE".equals(disponibilidade.getStatus())) {
            throw new RuntimeException("Horário não disponível para reserva");
        }

        disponibilidade.setStatus("RESERVED");
        disponibilidadeRepository.save(disponibilidade);

        reserva.setUsuario(usuario);
        reserva.setDisponibilidade(disponibilidade);
        reserva.setDataHoraReserva(LocalDateTime.now());
        reserva.setStatus("CONFIRMADA");

        return reservaRepository.save(reserva);
    }

    @Transactional
    public Reserva realizarCheckin(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        reserva.setStatus("CHECKIN_REALIZADO");
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        Disponibilidade disponibilidade = reserva.getDisponibilidade();
        disponibilidade.setStatus("AVAILABLE");
        disponibilidadeRepository.save(disponibilidade);

        reserva.setStatus("Reserva Cancelada");
        reservaRepository.save(reserva);
    }

    public List<Reserva> findByUsuario(Long usuarioId) {
        return reservaRepository.findByUserId(usuarioId);
    }
}