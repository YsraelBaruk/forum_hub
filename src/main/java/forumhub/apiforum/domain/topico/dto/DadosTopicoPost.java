package forumhub.apiforum.domain.topico.dto;

import forumhub.apiforum.domain.curso.Curso;
import forumhub.apiforum.domain.status.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

public record DadosTopicoPost(
        @NotBlank @UniqueElements String titulo,
        @NotBlank @UniqueElements String mensagem,
        @NotBlank LocalDate dataCriacao,
        @NotBlank String autor,
        @NotBlank Curso curso,
        @NotBlank StatusTopico status
) {
}
