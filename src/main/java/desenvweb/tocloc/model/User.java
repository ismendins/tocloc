package desenvweb.tocloc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "usuarios")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String role;

    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "owner")
    private List<Local> locais = new ArrayList<>();

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }
}