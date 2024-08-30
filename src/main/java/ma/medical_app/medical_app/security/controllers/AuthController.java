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
import ma.medical_app.medical_app.security.dto.AuthenticationRequest;
import ma.medical_app.medical_app.security.dto.TokenResponse;
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

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestBody User user) throws JsonProcessingException {
        String jwtToken = authenticationService.register(user);
        if (jwtToken == null)
            return ResponseEntity.badRequest().body("User Already exists");
        else
            return ResponseEntity.ok(new TokenResponse(jwtToken));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request);
        String jwtToken = authenticationService.authenticate(request);
        if (jwtToken == null) {
            return ResponseEntity.badRequest().body("Email or password incorrect");
        } else {
            return ResponseEntity.ok(new TokenResponse(jwtToken));
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
