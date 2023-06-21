package com.mantenimiento.spring.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mantenimiento.spring.models.Reserva;

import com.mantenimiento.spring.models.exceptions.ResorceNotFoundException;
import com.mantenimiento.spring.repositorio.ReservaRepositorio;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/api/reserva")
public class ReservaRestController {

    @Autowired
    private ReservaRepositorio reservaRepositoriy;

    @GetMapping()
    public List<Reserva> getAllReserva() {
        return reservaRepositoriy.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaId(@PathVariable(value = "id") Integer reservaId)
            throws ResorceNotFoundException {

        Reserva reserva = reservaRepositoriy.findById(reservaId)
                .orElseThrow(() -> new ResorceNotFoundException("Reserva no encontrado por Id: " + reservaId));
        return ResponseEntity.ok(reserva);

    }

    @PostMapping()
    public Reserva createReserva(@Validated @RequestBody Reserva reserva) {
        return reservaRepositoriy.save(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable(value = "id") Integer reservaId,
            @Validated @RequestBody Reserva reservaDetails) throws ResorceNotFoundException {
        Reserva reserva = reservaRepositoriy.findById(reservaId)
                .orElseThrow(() -> new ResorceNotFoundException("Reserva no encontrado por Id : " + reservaId));

        reserva.setId_cliente(reservaDetails.getId_cliente());
        reserva.setId_sede(reservaDetails.getId_sede());
        reserva.setId_vehiculo(reservaDetails.getId_vehiculo());
        reserva.setFechaIngreso(reservaDetails.getFechaIngreso());
        reserva.setTurno(reservaDetails.getTurno());
        final Reserva updateReserva = reservaRepositoriy.save(reserva);
        return ResponseEntity.ok(updateReserva);

    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteReserva(@PathVariable(value = "id") Integer reservaId)
            throws ResorceNotFoundException {
        Reserva reserva = reservaRepositoriy.findById(reservaId)
                .orElseThrow(() -> new ResorceNotFoundException("Reserva no encontrado por Id : " + reservaId));
        reservaRepositoriy.delete(reserva);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
