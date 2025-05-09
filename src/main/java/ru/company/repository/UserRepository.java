package ru.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.company.domain.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Override
    List<User> findAll();
}
