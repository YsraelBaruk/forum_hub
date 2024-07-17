package forumhub.apiforum.controller;

import forumhub.apiforum.domain.topico.Topico;
import forumhub.apiforum.domain.topico.dto.TopicoDTO;
import forumhub.apiforum.domain.topico.TopicoRepository;
import forumhub.apiforum.domain.topico.dto.DadosTopicoPost;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> postTopico(@RequestBody @Valid DadosTopicoPost dadosTopico, UriComponentsBuilder uriBuilder){
            var topico = repository.save(new Topico(dadosTopico));
            var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }
}