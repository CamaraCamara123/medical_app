package ma.medical_app.medical_app;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.medical_app.medical_app.security.entities.Role;
import ma.medical_app.medical_app.security.entities.User;
import ma.medical_app.medical_app.security.repositories.RoleRepository;
import ma.medical_app.medical_app.security.repositories.UserRepository;

@SpringBootApplication
public class MedicalAppApplication {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MedicalAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			Role adminRole = Role.builder().name("ROLE_ADMIN").build();
			adminRole = roleRepository.save(adminRole);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			var admin = User.builder()
					.firstName("Camara")
					.lastName("Aboudramane")
					.username("camso123").email("admin@gmail.com")
					.password(passwordEncoder.encode("admin"))
					.roles(Set.of(adminRole))
					.build();
			userRepository.save(admin);
		};
	}
}
