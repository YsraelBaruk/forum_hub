package forumhub.apiforum.domain.topico.dto;

import forumhub.apiforum.domain.curso.Curso;
import forumhub.apiforum.domain.status.StatusTopico;
import forumhub.apiforum.domain.topico.Topico;

import java.time.LocalDate;

public record TopicoDTO(
            String titulo,
            String mensagem,
            LocalDate dataCriacao,
            String autor,
            Curso curso,
            StatusTopico status
) {
    public TopicoDTO(Topico t){
        this(t.getTitulo(), t.getMensagem(), t.getDataCriacao(), t.getAutor(), t.getCurso(), t.getSatus());
    }
}
