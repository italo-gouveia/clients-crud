package br.com.snet.challenge.repository;

import br.com.snet.challenge.data.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
