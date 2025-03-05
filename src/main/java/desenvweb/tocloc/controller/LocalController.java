package desenvweb.tocloc.controller;

import desenvweb.tocloc.model.Local;
import desenvweb.tocloc.model.User;
import desenvweb.tocloc.service.LocalService;
import desenvweb.tocloc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locais")
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @PostMapping
    public ResponseEntity<Local> create(@RequestBody Local local) {
        return new ResponseEntity<>(localService.create(local), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> getById(@PathVariable Long id) {
        return ResponseEntity.ok(localService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Local>> getAll() {
        return ResponseEntity.ok(localService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> update(@PathVariable Long id, @RequestBody Local local) {
        return ResponseEntity.ok(localService.update(id, local));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        localService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
