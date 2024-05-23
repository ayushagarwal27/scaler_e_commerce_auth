package org.ayush.e_commerce_auth.repositories;

import org.ayush.e_commerce_auth.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
