package com.ayanokoujifl.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayanokoujifl.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
