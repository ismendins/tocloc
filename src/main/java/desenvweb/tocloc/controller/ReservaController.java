package desenvweb.tocloc.controller;

import desenvweb.tocloc.model.Reserva;
import desenvweb.tocloc.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
            this.reservaService = reservaService;
    }

        @PostMapping
        public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
            return new ResponseEntity<>(reservaService.create(reserva), HttpStatus.CREATED);
        }

        @PostMapping("/{id}/checkin")
        public ResponseEntity<Reserva> checkin(@PathVariable Long id) {
            return ResponseEntity.ok(reservaService.realizarCheckin(id));
        }

        @PostMapping("/{id}/cancelar")
        public ResponseEntity<Void> cancelar(@PathVariable Long id) {
            reservaService.cancelarReserva(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/usuario/{usuarioId}")
        public ResponseEntity<List<Reserva>> getByUsuario(@PathVariable Long usuarioId) {
            return ResponseEntity.ok(reservaService.findByUsuario(usuarioId));
        }
    }
