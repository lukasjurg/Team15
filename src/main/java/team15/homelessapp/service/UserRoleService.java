package team15.homelessapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team15.homelessapp.model.UserRole;
import team15.homelessapp.repos.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    public List<UserRole> getAllRoles() {
        return userRoleRepository.findAll();
    }

    public Optional<UserRole> getRoleById(int id) {
        return userRoleRepository.findById(id);
    }

    public UserRole createRole(UserRole role) {
        return userRoleRepository.save(role);
    }

    public Optional<UserRole> updateRole(int id, UserRole updatedRole) {
        return userRoleRepository.findById(id).map(role -> {
            role.setRole_name(updatedRole.getRole_name());
            return userRoleRepository.save(role);
        });
    }

    public boolean deleteRole(int id) {
        if (userRoleRepository.existsById(id)) {
            userRoleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
