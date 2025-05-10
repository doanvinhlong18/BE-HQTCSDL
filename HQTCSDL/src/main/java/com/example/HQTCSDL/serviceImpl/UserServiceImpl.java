package com.example.HQTCSDL.serviceImpl;

import com.example.HQTCSDL.Dto.AuthResponseDTO;
import com.example.HQTCSDL.Dto.LoginDto;
import com.example.HQTCSDL.Dto.ResponseDto.UserResponseDto;
import com.example.HQTCSDL.Dto.UserDto;
import com.example.HQTCSDL.Entity.UserEntity;
import com.example.HQTCSDL.Security.JwtAuthEntryPoint;
import com.example.HQTCSDL.Security.JwtGenerator;
import com.example.HQTCSDL.repository.UserRepository;
import com.example.HQTCSDL.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @Override
    public ResponseEntity<?> login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        return new ResponseEntity<>(new AuthResponseDTO(token, roles), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> logout() {
        return null;
    }

    @Override
    public ResponseEntity<?> register(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> addUser(UserDto userDto) {
        try{
            if (validateSignup(userDto)) {
                if(userRepository.existsByUsername(userDto.getUsername())){
                    return new ResponseEntity<>(Collections.singletonMap("message", "Username already exists"), HttpStatus.CONFLICT);
                }
                UserEntity userEntity = userRepository.findByEmail(userDto.getEmail());
                if (Objects.isNull(userEntity)) {
                    userRepository.save(getUserFromDto(userDto));
                    return new ResponseEntity<>(Collections.singletonMap("message", "Them thanh cong"), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(Collections.singletonMap("message", "Email da ton tai"), HttpStatus.CONFLICT);
                }
            } else {
                return new ResponseEntity<>(Collections.singletonMap("message", "Du lieu khong hop le"), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(Collections.singletonMap("message", "some thing went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateUserById(int id, UserDto userDto) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Không tìm thấy user."), HttpStatus.NOT_FOUND);
        }
        UserEntity user = userEntity.get();
        user.setEmail(userDto.getEmail());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        user.setPosition(userDto.getPosition());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUserById(int id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "không tìm thấy người dùng"), HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(Collections.singletonMap("message", "Xóa thành công"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(int id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay user"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userEntity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong ton tai user nao"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isEmpty()){
            return new ResponseEntity<>(Collections.singletonMap("message", "Khong tim thay tai khoan nguoi dung"), HttpStatus.NOT_FOUND);
        }
        UserEntity user = userEntity.get();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setDateOfBirth(user.getDateOfBirth());
        userResponseDto.setFullName(user.getFullName());
        userResponseDto.setPhone(user.getPhone());
        userResponseDto.setPosition(user.getPosition());
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    public boolean validateSignup(UserDto userDto) {
        if(!userDto.getEmail().isBlank()
                && !userDto.getPassword().isBlank()
                && !userDto.getUsername().isBlank()
                && !userDto.getFullName().isBlank()
                && userDto.getDateOfBirth() != null
                && !userDto.getPhone().isBlank()
                && !userDto.getPosition().isBlank()){
            return true;
        }
        return false;
    }
    public UserEntity getUserFromDto(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setUsername(userDto.getUsername());
        userEntity.setDateOfBirth(userDto.getDateOfBirth());
        userEntity.setFullName(userDto.getFullName());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setPosition(userDto.getPosition());
        return userEntity;
    }
}
