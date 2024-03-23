package com.tez.dieticianpatientapp.controller;

import com.tez.dieticianpatientapp.dto.Request.DieticianRegisterRequest;
import com.tez.dieticianpatientapp.dto.Request.LoginRequest;
import com.tez.dieticianpatientapp.dto.Response.AuthResponse;
import com.tez.dieticianpatientapp.entities.Dietician;
import com.tez.dieticianpatientapp.entities.User;
import com.tez.dieticianpatientapp.service.DieticianService;
import com.tez.dieticianpatientapp.service.UserService;
import com.tez.dieticianpatientapp.utils.JwtUtil;
import com.tez.dieticianpatientapp.utils.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtTokenProvider;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private DieticianService dieticianService;


    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, DieticianService dieticianService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.dieticianService = dieticianService;
    }

    @PostMapping("api/v1/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.tckn(), loginRequest.password());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateToken(auth);
        User user = userService.getUserByTckn(loginRequest.tckn()).get();
        AuthResponse authResponse = new AuthResponse("Kullanıcı başarı ile giriş yaptı",jwtToken,user.getId());
        return authResponse;
    }

    @PostMapping("api/v1/register/dietician")
    public ResponseEntity<AuthResponse> register(@RequestBody DieticianRegisterRequest registerRequest) {

        User user = new User();
        user.setTckn(registerRequest.tckn());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setUserType(UserType.Dietician);
        Dietician dietician = new Dietician(registerRequest.firstName(),registerRequest.lastName(),user);
        dieticianService.saveDietician(dietician);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.tckn(), registerRequest.password());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateToken(auth);
        AuthResponse authResponse = new AuthResponse("Kullanıcı Başarı İle Kayıt Oldu",jwtToken,dietician.getId());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
}
