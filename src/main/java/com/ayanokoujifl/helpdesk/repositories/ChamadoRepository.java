package com.ayanokoujifl.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayanokoujifl.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
