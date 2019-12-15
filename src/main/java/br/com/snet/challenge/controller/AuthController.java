package br.com.snet.challenge.controller;

import br.com.snet.challenge.data.model.User;
import br.com.snet.challenge.repository.UserRepository;
import br.com.snet.challenge.security.AccountCredentialsVO;
import br.com.snet.challenge.security.jwt.JwtTokenProvider;
import br.com.snet.challenge.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Api(tags = "AuthenticationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @Autowired
    UserServices service;

    @ApiOperation(value = "Autentica um usuário e retorna um token")
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
        try {
            String username = data.getUsername();
            String email = data.getEmail();
            String password = data.getPassword();

            User user = service.findByUsernameOrEmail(username, email);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), password));

            String token = "";

            if (user != null) {
                token = tokenProvider.createToken(user.getUsername(), user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + user.getUserName() + " not found!");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", user.getUserName());
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    //Endpoint ocultado do swagger para ser acessado apenas via frontend
    @ApiIgnore
    @Transactional
    @ApiOperation(value = "Cria um usuário e retorna um token")
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signup", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity signup(@RequestBody AccountCredentialsVO user) {

        User data = service.save(user);

        Map<Object, Object> model = new HashMap<>();
        model.put("username", data.getUserName());
        return ok(model);
    }
}