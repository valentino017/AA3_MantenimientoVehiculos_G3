package com.mantenimiento.spring.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mantenimiento.spring.models.Personal;

public interface PersonalRepositorio extends JpaRepository<Personal, Integer> {

    // @Query(value = "SELECT a FROM Personal a WHERE a.nombre=?1")
    // public List<Personal> buscarPersonalPorNombre(String nombre);

    // @Query(value = "SELECT a FROM Personal a WHERE a.nombre like CONCAT(?1,'%')")
    // public List<Personal> buscarPersonalLikeNombre(String nombre);
}