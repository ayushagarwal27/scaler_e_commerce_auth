package org.ayush.e_commerce_auth.repositories;

import org.ayush.e_commerce_auth.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepo extends JpaRepository<Session, Long> {
}
