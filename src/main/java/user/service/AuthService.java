package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import user.exception.AppException;
import user.model.Role;
import user.model.User;
import user.payload.ApiResponse;
import user.payload.JwtAuthenticationResponse;
import user.payload.LoginRequest;
import user.payload.SignUpRequest;
import user.repository.RoleRepository;
import user.repository.UserRepository;
import user.security.JwtTokenProvider;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    public ResponseEntity<?> authenticateUser (LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    public ResponseEntity<?> registerUser (SignUpRequest signUpRequest) {

        if (userRepository.existsByName(signUpRequest.getName())) {
            return new ResponseEntity(new ApiResponse(false, "This name already taken"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "This email already in use"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassHash(passwordEncoder.encode(user.getPassHash()));
        Role userRole = roleRepository.findRoleByName("ROLE_USER")
                .orElseThrow(() -> new AppException("User role not set"));

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        user.setRoles(userRoles);

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("api/users/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
