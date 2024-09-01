package ma.medical_app.medical_app.security.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.medical_app.medical_app.entities.Doctor;
import ma.medical_app.medical_app.entities.Patient;
import ma.medical_app.medical_app.repositories.DoctorRepository;
import ma.medical_app.medical_app.repositories.PatientRepository;
import ma.medical_app.medical_app.security.dto.AuthenticationRequest;
import ma.medical_app.medical_app.security.entities.Token;
import ma.medical_app.medical_app.security.entities.User;
import ma.medical_app.medical_app.security.repositories.RoleRepository;
import ma.medical_app.medical_app.security.repositories.TokenRepository;
import ma.medical_app.medical_app.security.repositories.UserRepository;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    public String registerDoctor(Doctor user) {
        if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).orElse(null) != null) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userRole = roleRepository.findByName("ROLE_DOCTOR").orElse(null);
        user.setRoles(Set.of(userRole));
        var savedUser = doctorRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return jwtToken;
    }

    public String registerPatient(Patient user) {
        if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).orElse(null) != null) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userRole = roleRepository.findByName("ROLE_PATIENT").orElse(null);
        user.setRoles(Set.of(userRole));
        var savedUser = patientRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return jwtToken;
    }

    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public String authenticate(AuthenticationRequest request) {
        try {
            System.out.println(request);
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByUsernameOrEmail(request.getEmail(), request.getEmail()).orElse(null);
            // System.out.println("a00na00na00na00na00na00na00na00na00na00na00na00na00na00na00n");
            System.out.println(user);
            var jwtToken = jwtService.generateToken(user);
            // revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return jwtToken;
        } catch (Exception e) {
            System.out
                    .println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + e.getMessage());
            return null;
        }
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
