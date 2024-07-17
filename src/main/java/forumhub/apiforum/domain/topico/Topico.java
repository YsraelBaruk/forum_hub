package forumhub.apiforum.domain.topico;


import forumhub.apiforum.domain.curso.Curso;
import forumhub.apiforum.domain.status.StatusTopico;
import forumhub.apiforum.domain.topico.dto.DadosTopicoPost;
import forumhub.apiforum.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Topico")
@Table(name = "topicos")
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDate dataCriacao;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    @Enumerated(EnumType.STRING)
    private StatusTopico satus;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "idCurso", unique = true)
    private Usuario usuario;

    public Topico(DadosTopicoPost topico) {
        this.titulo = topico.titulo();
        this.mensagem = topico.mensagem();
        this.dataCriacao = topico.dataCriacao();
        this.autor = topico.autor();
        this.curso = topico.curso();
        this.satus = topico.status();
    }
}
