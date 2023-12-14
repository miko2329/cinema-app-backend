package kz.meirbek.identityservice.dto;

import kz.meirbek.identityservice.entity.Role;
import kz.meirbek.identityservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;

    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole());
    }
}
