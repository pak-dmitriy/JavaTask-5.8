package habsida.spring.boot_security.demo.dto;

import habsida.spring.boot_security.demo.model.User;

import java.util.Set;

public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Set<String> roles;

    public UserResponseDto(Long id, String firstName, String lastName, String email, int age, Set<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }



    public Set<String> getRoles() {
        return roles;
    }

    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge(),
                user.getRoles().stream().map(role -> role.getName()).collect(java.util.stream.Collectors.toSet())
        );
    }
}
