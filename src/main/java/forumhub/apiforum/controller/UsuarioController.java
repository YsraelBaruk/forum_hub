package forumhub.apiforum.controller;

import forumhub.apiforum.domain.usuario.dto.DadosUsuario;
import forumhub.apiforum.domain.usuario.Usuario;
import forumhub.apiforum.service.infra.dto.DadosToken;
import forumhub.apiforum.domain.usuario.UsuarioRepository;
import forumhub.apiforum.service.infra.security.TokenServiceAuth;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenServiceAuth tokenServiceAuth;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity logar(@RequestBody @Valid DadosUsuario dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = manager.authenticate(usernamePassword);
        var tokenJWT = tokenServiceAuth.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DadosUsuario dados){
        if (this.repository.findByEmail(dados.email()) != null) return ResponseEntity.badRequest().build();
        String senhaCripto = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario u = new Usuario(dados.nome(), dados.email(), senhaCripto);
        this.repository.save(u);
        return ResponseEntity.ok().build();
    }
}