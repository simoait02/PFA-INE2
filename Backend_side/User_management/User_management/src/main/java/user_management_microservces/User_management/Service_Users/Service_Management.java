package user_management_microservces.User_management.Service_Users;


import org.springframework.stereotype.Service;
import user_management_microservces.User_management.DTO.dtoUser;
import user_management_microservces.User_management.Dao.Jpa_Users;
import user_management_microservces.User_management.Enities.Users;
import user_management_microservces.User_management.Mappers.Mapper_Users;
import user_management_microservces.User_management.DTO.AccountDetails;

import java.util.List;
import java.util.stream.Collectors;

import java.util.NoSuchElementException;

@Service
public class Service_Management {
    private final Mapper_Users userMapper;
    private final Jpa_Users userRepository;
    private final Jwt_Validator jwtValidator;

    public Service_Management(Mapper_Users userMapper, Jpa_Users userRepository, Jwt_Validator jwtValidator) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.jwtValidator = jwtValidator;
    }

    public List<dtoUser> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDtoUser)
                .collect(Collectors.toList());
    }

    public dtoUser getUserById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDtoUser)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public dtoUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDtoUser)
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    public AccountDetails getAccountDetailsForLogin(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        return userRepository.findByEmail(email)
                .map(user -> AccountDetails.builder()
                        .email(email)
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .bio(user.getBio())
                        .role(user.getRole())
                        .birthDate(user.getBirthDate())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
    }

    public void createUser(AccountDetails userDetails) {
        Users user = userMapper.toUser_authentication(userDetails);
        userRepository.save(user);
    }

    public dtoUser updateUser(dtoUser userDto) {
        Users user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userDto.getId()));

        userMapper.updateFromDto(user, userDto);
        userRepository.save(user);

        return userMapper.toDtoUser(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public boolean validateToken(String token) {
        String email = jwtValidator.extractSubject(token);
        return userRepository.findByEmail(email)
                .map(user -> jwtValidator.validateToken(token, user.getEmail()))
                .orElse(false);
    }

    public String extractTokenFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        throw new IllegalArgumentException("Invalid authorization header");
    }

    public String extractEmailFromToken(String token) {
        return jwtValidator.extractSubject(token);
    }
}
