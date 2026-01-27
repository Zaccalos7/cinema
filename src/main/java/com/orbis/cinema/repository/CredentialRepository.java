package com.orbis.cinema.repository;

import com.orbis.cinema.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    Credential findByEmail(String email);
}
