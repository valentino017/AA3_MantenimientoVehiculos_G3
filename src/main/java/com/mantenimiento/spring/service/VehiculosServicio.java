package com.mantenimiento.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.spring.models.Vehiculo;
import com.mantenimiento.spring.repositorio.VehiculoRepositorio;

import javax.transaction.Transactional;

@Service
@Transactional
public class VehiculosServicio {
	@Autowired
    private VehiculoRepositorio repositorio;

    public VehiculosServicio() {

    }

    public List<Vehiculo> buscarTodo() {
        return (List<Vehiculo>) repositorio.findAll();
    }

    public Vehiculo crear(Vehiculo vehiculo) {
        return repositorio.save(vehiculo);
    }

    public Vehiculo actualizar(Vehiculo vehiculoActualizar) {
    	Vehiculo vehiculoActual = repositorio.findById(vehiculoActualizar.getId_vehiculo()).get();

    	vehiculoActual.setId_vehiculo(vehiculoActualizar.getId_vehiculo());
    	vehiculoActual.setModelo(vehiculoActualizar.getModelo());
    	vehiculoActual.setEstado(vehiculoActualizar.getEstado());
    	vehiculoActual.setNroMatricula(vehiculoActualizar.getNroMatricula());
    	vehiculoActual.setId_cliente(vehiculoActualizar.getId_cliente());
    	vehiculoActual.setColor(vehiculoActualizar.getColor());

        // Pelicula peliculaActualizado = repositorio.save(peliculaActual);
    	Vehiculo vehiculoActualizado = repositorio.save(vehiculoActual);
        return vehiculoActualizado;

    }

    public Vehiculo buscarPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    public void borrarPorId(Integer id) {
        repositorio.deleteById(id);
    }

}
