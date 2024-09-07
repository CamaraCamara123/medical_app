package ma.medical_app.medical_app.security.dto;

import lombok.*;
import ma.medical_app.medical_app.security.entities.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticated {
    private User user;
    private String token;

}