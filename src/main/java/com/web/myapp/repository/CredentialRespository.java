package com.web.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.myapp.model.Credential;

public interface CredentialRespository extends JpaRepository<Credential, Integer> {
   Credential findByUsername(String username);
}
