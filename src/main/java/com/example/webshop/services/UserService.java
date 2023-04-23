package com.example.webshop.services;


import com.example.webshop.models.models.Role;
import com.example.webshop.models.models.User;
import com.example.webshop.models.dto.UserDto;
import com.example.webshop.models.models.Vegetable;
import com.example.webshop.repositories.RoleRepository;
import com.example.webshop.repositories.UserRepository;
import com.example.webshop.util.NotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_CUSTOMER");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    public void removeAdminRole(User user) {
        List<Role> roles = new ArrayList<>(user.getRoles());
        roles.remove(roleRepository.findByName("ROLE_ADMIN"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void giveUserAdminRole(User user) {
        List<Role> roles = new ArrayList<>(user.getRoles());
        roles.add(roleRepository.findByName("ROLE_ADMIN"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public void deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Item not found");
        }
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setId(user.getId());
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }

    public void updateUser(User user) {
        User currentUser = getCurrentUser();
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        if (!user.getPassword().isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(currentUser);
    }
}