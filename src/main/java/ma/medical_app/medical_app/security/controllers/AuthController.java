package ma.medical_app.medical_app.security.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.medical_app.medical_app.entities.Doctor;
import ma.medical_app.medical_app.entities.Patient;
import ma.medical_app.medical_app.security.dto.AuthenticationRequest;
import ma.medical_app.medical_app.security.dto.TokenResponse;
import ma.medical_app.medical_app.security.dto.UserAuthenticated;
import ma.medical_app.medical_app.security.entities.Role;
import ma.medical_app.medical_app.security.entities.User;
import ma.medical_app.medical_app.security.services.AuthenticationService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class UserInfo {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Set<Role> roles;

}

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register/doctor")
    public ResponseEntity<Object> registerDoctor(
            @RequestBody Doctor user) throws JsonProcessingException {
        UserAuthenticated userauth = authenticationService.registerDoctor(user);
        if (userauth == null)
            return ResponseEntity.badRequest().body("User Already exists");
        else
            return ResponseEntity.ok(new TokenResponse(userauth));
    }

    @PostMapping("/register/patient")
    public ResponseEntity<Object> registerPatient(
            @RequestBody Patient user) throws JsonProcessingException {
        UserAuthenticated userauth = authenticationService.registerPatient(user);
        if (userauth == null)
            return ResponseEntity.badRequest().body("User Already exists");
        else
            return ResponseEntity.ok(new TokenResponse(userauth));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request);
        UserAuthenticated userauth = authenticationService.authenticate(request);
        if (userauth == null) {
            return ResponseEntity.badRequest().body("Email or password incorrect");
        } else {
            return ResponseEntity.ok(new TokenResponse(userauth));
        }
    }

    @GetMapping("/userinfo")
    public UserInfo userinfo(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        // user.setTodos(null);
        return UserInfo.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

}
