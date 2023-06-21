package com.mantenimiento.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.spring.models.Sede;
import com.mantenimiento.spring.repositorio.SedeRepositorio;

import javax.transaction.Transactional;

@Service
@Transactional
public class SedesServicio {
	@Autowired
    private SedeRepositorio repositorio;

    public SedesServicio() {

    }

    public List<Sede> buscarTodo() {
        return (List<Sede>) repositorio.findAll();
    }

    public Sede crear(Sede sede) {
        return repositorio.save(sede);
    }

    public Sede actualizar(Sede sedeActualizar) {
        Sede sedeActual = repositorio.findById(sedeActualizar.getId_sede()).get();

        sedeActual.setId_sede(sedeActualizar.getId_sede());
        sedeActual.setNombre(sedeActualizar.getNombre());
        sedeActual.setDireccion(sedeActualizar.getDireccion());
        sedeActual.setId_personal(sedeActualizar.getId_personal());

        // Pelicula peliculaActualizado = repositorio.save(peliculaActual);
        Sede sedeActualizado = repositorio.save(sedeActual);
        return sedeActualizado;

    }

    public Sede buscarPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    public void borrarPorId(Integer id) {
        repositorio.deleteById(id);
    }

}
