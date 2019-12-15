package br.com.snet.challenge.services;

import br.com.snet.challenge.data.model.Permission;
import br.com.snet.challenge.data.model.User;
import br.com.snet.challenge.exception.ResourceNotFoundException;
import br.com.snet.challenge.repository.PermissionRepository;
import br.com.snet.challenge.repository.UserRepository;
import br.com.snet.challenge.security.AccountCredentialsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Autowired
    PermissionRepository permissionRepository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if(user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
    }

    public User findByUsernameOrEmail(String username, String email) throws UsernameNotFoundException {
        User user = repository.findByUsernameOrEmail(username, email);
        if(user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " or email " + email + " not found");
        }
    }

    public User save(AccountCredentialsVO vo) {
        User user = new User();
        user.setEmail(vo.getEmail());
        user.setFullName(vo.getFullname());
        user.setUserName(vo.getUsername());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(vo.getPassword());
        user.setPassword(encryptedPassword);

        Permission admin = permissionRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        Permission manager = permissionRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        List<Permission> permissions = new ArrayList<>();
        permissions.add(admin);
        permissions.add(manager);

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setPermissions(permissions);
        return repository.save(user);
    }
}
