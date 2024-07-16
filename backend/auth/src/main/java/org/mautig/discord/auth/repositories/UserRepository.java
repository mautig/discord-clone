package org.mautig.discord.auth.repositories;

import java.util.Optional;

import org.mautig.discord.auth.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByEmail(String email);
}
