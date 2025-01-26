package com.example.SistemaReservaAutomotiva.services;

import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.example.SistemaReservaAutomotiva.domain.user.UserAccountStatus;
import com.example.SistemaReservaAutomotiva.domain.user.UserType;
import com.example.SistemaReservaAutomotiva.dto.input.LoginUserDTO;
import com.example.SistemaReservaAutomotiva.dto.input.NewUserDTO;
import com.example.SistemaReservaAutomotiva.dto.output.TokenResponseDTO;
import com.example.SistemaReservaAutomotiva.exceptions.NotFound404Exception;
import com.example.SistemaReservaAutomotiva.repositories.UserRepository;
import com.example.SistemaReservaAutomotiva.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public TokenResponseDTO createNewUser(NewUserDTO data){
        try{
            String token = jwtService.generateToken(data.email());

            User user = User.builder()
                    .name(data.name())
                    .password(encryptPassword(data.password()))
                    .mail(data.email())
                    .cep(data.cep())
                    .phone(data.phone())
                    .type(UserType.CLIENT)
                    .register_date(LocalDate.now())
                    .status(UserAccountStatus.ALLOWED)
                    .userRoles(data.role())
                    .build();

            userRepository.save(user);

            return TokenResponseDTO.builder()
                    .token(token)
                    .code(200)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFound404Exception("User not found"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String mail){
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new NoSuchElementException("There is no user with specified mail"));
    }

    public User getUserByToken(String token){
        return getUserByEmail(jwtService.getTokenSubject(token));
    }

    public TokenResponseDTO loginUser(LoginUserDTO data){
        String password = encryptPassword(data.password());

        if(!userRepository.existsByPasswordAndMail(password, data.email())){
           throw new IllegalArgumentException("Incorrect password or mail");
        }

        String token = jwtService.generateToken(data.email());

        return TokenResponseDTO.builder()
                .code(200).token(token)
                .build();
    }

    public String encryptPassword(String password){
        return password;
    }

    public String decryptPassword(String encryptedPassword){
        return encryptedPassword;
    }

}
