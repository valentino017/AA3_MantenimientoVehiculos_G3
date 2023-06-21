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
import com.mantenimiento.spring.models.Vehiculo;
import com.mantenimiento.spring.models.exceptions.ResorceNotFoundException;
import com.mantenimiento.spring.repositorio.VehiculoRepositorio;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoRestController {
    @Autowired
    private VehiculoRepositorio vehiculoRepositoriy;

    @GetMapping()
    public List<Vehiculo> getAllvehiculo() {
        return vehiculoRepositoriy.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoId(@PathVariable(value = "id") Integer vehiculoId)
            throws ResorceNotFoundException {

        Vehiculo vehiculo = vehiculoRepositoriy.findById(vehiculoId)
                .orElseThrow(() -> new ResorceNotFoundException("Vehiculo no encontrado por Id: " + vehiculoId));
        return ResponseEntity.ok(vehiculo);

    }

    @PostMapping()
    public Vehiculo createVehiculo(@Validated @RequestBody Vehiculo vehiculo) {
        return vehiculoRepositoriy.save(vehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable(value = "id") Integer vehiculoId,
            @Validated @RequestBody Vehiculo vehiculoDetails) throws ResorceNotFoundException {
        Vehiculo vehiculo = vehiculoRepositoriy.findById(vehiculoId)
                .orElseThrow(() -> new ResorceNotFoundException("Vehiculo no encontrado por Id : " + vehiculoId));

        vehiculo.setModelo(vehiculoDetails.getModelo());
        vehiculo.setEstado(vehiculoDetails.getEstado());
        vehiculo.setNroMatricula(vehiculoDetails.getNroMatricula());
        vehiculo.setId_cliente(vehiculoDetails.getId_cliente());
        vehiculo.setColor(vehiculoDetails.getColor());

        final Vehiculo updateVehiculo = vehiculoRepositoriy.save(vehiculo);
        return ResponseEntity.ok(updateVehiculo);

    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteVehiculo(@PathVariable(value = "id") Integer vehiculoId)
            throws ResorceNotFoundException {
        Vehiculo vehiculo = vehiculoRepositoriy.findById(vehiculoId)
                .orElseThrow(() -> new ResorceNotFoundException("Vehiculo no encontrado por Id : " + vehiculoId));
        vehiculoRepositoriy.delete(vehiculo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

}
