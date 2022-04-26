package com.example.lendinglibrary.repositories;

import com.example.lendinglibrary.models.LibraryUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

  Optional<LibraryUser> findLibraryUserByEmail(String email);

  Optional<LibraryUser> findLibraryUserById(Long id);

  boolean existsByEmailAndPassword(String email, String password);

  boolean existsByEmail(String email);
}
