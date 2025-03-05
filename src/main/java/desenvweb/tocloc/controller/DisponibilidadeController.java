package desenvweb.tocloc.controller;

import desenvweb.tocloc.model.Disponibilidade;
import desenvweb.tocloc.service.DisponibilidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;

    public DisponibilidadeController(DisponibilidadeService disponibilidadeService) {
        this.disponibilidadeService = disponibilidadeService;
    }

    @PostMapping
    public ResponseEntity<Disponibilidade> create(@RequestBody Disponibilidade disponibilidade) {
        return new ResponseEntity<>(disponibilidadeService.create(disponibilidade), HttpStatus.CREATED);
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<List<Disponibilidade>> getByLocal(@PathVariable Long localId) {
        return ResponseEntity.ok(disponibilidadeService.findByLocal(localId));
    }
}