package com.mantenimiento.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.spring.models.Reserva;
import com.mantenimiento.spring.repositorio.ReservaRepositorio;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservasServicio {
    @Autowired
    private ReservaRepositorio repositorio;

    public ReservasServicio() {

    }

    public List<Reserva> buscarTodo() {
        return (List<Reserva>) repositorio.findAll();
    }

    public Reserva crear(Reserva reserva) {
        return repositorio.save(reserva);
    }

    public Reserva actualizar(Reserva reservaActualizar) {
        Reserva reservaActual = repositorio.findById(reservaActualizar.getId_reserva()).get();

        reservaActual.setId_reserva(reservaActualizar.getId_reserva());
        reservaActual.setId_cliente(reservaActualizar.getId_cliente());
        reservaActual.setId_sede(reservaActualizar.getId_sede());
        reservaActual.setId_vehiculo(reservaActualizar.getId_vehiculo());
        reservaActual.setFechaIngreso(reservaActualizar.getFechaIngreso());
        reservaActual.setTurno(reservaActualizar.getTurno());

        // Pelicula peliculaActualizado = repositorio.save(peliculaActual);
        Reserva reservaActualizado = repositorio.save(reservaActual);
        return reservaActualizado;

    }

    public Reserva buscarPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    public void borrarPorId(Integer id) {
        repositorio.deleteById(id);
    }

}
