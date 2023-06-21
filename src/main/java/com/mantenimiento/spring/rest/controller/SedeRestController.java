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

import com.mantenimiento.spring.models.Sede;
import com.mantenimiento.spring.models.exceptions.ResorceNotFoundException;
import com.mantenimiento.spring.repositorio.SedeRepositorio;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/api/sede")
public class SedeRestController {

    @Autowired
    private SedeRepositorio sedeRepositoriy;

    @GetMapping()
    public List<Sede> getAllSede() {
        return sedeRepositoriy.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> getSedeId(@PathVariable(value = "id") Integer sedeId)
            throws ResorceNotFoundException {

        Sede sede = sedeRepositoriy.findById(sedeId)
                .orElseThrow(() -> new ResorceNotFoundException("Sede no encontrado por Id: " + sedeId));
        return ResponseEntity.ok(sede);

    }

    @PostMapping()
    public Sede createSede(@Validated @RequestBody Sede sede) {
        return sedeRepositoriy.save(sede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sede> updateSede(@PathVariable(value = "id") Integer sedeId,
            @Validated @RequestBody Sede sedeDetails) throws ResorceNotFoundException {
        Sede sede = sedeRepositoriy.findById(sedeId)
                .orElseThrow(() -> new ResorceNotFoundException("Sede no encontrado por Id : " + sedeId));

        sede.setNombre(sedeDetails.getNombre());
        sede.setDireccion(sedeDetails.getDireccion());
        sede.setId_personal(sedeDetails.getId_personal());
        final Sede updateSede = sedeRepositoriy.save(sede);
        return ResponseEntity.ok(updateSede);

    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteSede(@PathVariable(value = "id") Integer sedeId)
            throws ResorceNotFoundException {
        Sede sede = sedeRepositoriy.findById(sedeId)
                .orElseThrow(() -> new ResorceNotFoundException("Sede no encontrado por Id : " + sedeId));
        sedeRepositoriy.delete(sede);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
