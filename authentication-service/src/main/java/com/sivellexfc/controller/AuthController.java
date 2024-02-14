package com.sivellexfc.controller;


import com.sivellexfc.model.UserRole;
import com.sivellexfc.dto.AuthRequest;
import com.sivellexfc.model.UserCredential;
import com.sivellexfc.service.AuthService;
import com.sivellexfc.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/register")
//    public String addNewUser(@RequestBody UserCredential user) {
//        return service.saveUser(user);
//    }

    @PostMapping("/register-customer")
    public String registerCustomerUser(@RequestBody UserCredential userCredential) {

        List<GrantedAuthority> authorities = UserRole.CUSTOMER.getAuthorities();
        userCredential.setAuthorities(UserRole.CUSTOMER);

        return service.saveUser(userCredential);
    }

    @PostMapping("/register-seller")
    public String registerSellerUser(@RequestBody UserCredential userCredential) {

        List<GrantedAuthority> authorities = UserRole.SELLER.getAuthorities();
        userCredential.setAuthorities(UserRole.SELLER);
        return service.saveUser(userCredential);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {

        System.out.println(authRequest.toString());

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {

            System.out.println("isAuthenticated..........................");
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam("token") String token) {
        System.out.println(token);
        System.out.println("validate token controller method");
        return service.validateToken(token);
    }

    @GetMapping("/getUserIdByAuthHeader")
    public String getAccountId(@RequestParam("authHeader") String token){
        return service.getUserIdByToken(token);
    }

    @GetMapping("get-roles")
    public List getRoles(@RequestParam("authHeader") String authHeader){
        System.out.println("get-roles section");
        return jwtService.getClaims(authHeader);
    }
}
