package com.tecnico.teste.cadastro.usuarios.user.repository;

import com.tecnico.teste.cadastro.usuarios.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
