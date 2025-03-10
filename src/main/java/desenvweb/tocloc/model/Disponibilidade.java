package desenvweb.tocloc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Double preco;
    private String status = "AVAILABLE";

    @OneToOne(mappedBy = "disponibilidade")
    private Reserva reserva;

    // Getters e Setters
}
