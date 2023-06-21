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
import com.mantenimiento.spring.models.Personal;
import com.mantenimiento.spring.models.exceptions.ResorceNotFoundException;
import com.mantenimiento.spring.repositorio.PersonalRepositorio;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/api/personal")
public class PersonalRestController {
    @Autowired
    private PersonalRepositorio personalRepository;

    @GetMapping()
    public List<Personal> getAllPersonales() {
        return personalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getPersonalId(@PathVariable(value = "id") Integer personalId)
            throws ResorceNotFoundException {

        Personal personal = personalRepository.findById(personalId)
                .orElseThrow(() -> new ResorceNotFoundException("Personal no encontrado por Id: " + personalId));
        return ResponseEntity.ok(personal);

    }

    @PostMapping()
    public Personal createPersonal(@Validated @RequestBody Personal personal) {
        return personalRepository.save(personal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> updatePersonal(@PathVariable(value = "id") Integer personalId,
            @Validated @RequestBody Personal personalDetails) throws ResorceNotFoundException {
        Personal personal = personalRepository.findById(personalId)
                .orElseThrow(() -> new ResorceNotFoundException("Personal no encontrado por Id : " + personalId));

        personal.setNombre(personalDetails.getNombre());
        personal.setApellido(personalDetails.getApellido());
        personal.setId_sede(personalDetails.getId_sede());
        personal.setCargo(personalDetails.getCargo());
        personal.setTurno(personalDetails.getTurno());

        final Personal updatePersonal = personalRepository.save(personal);
        return ResponseEntity.ok(updatePersonal);

    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePersonal(@PathVariable(value = "id") Integer personalId)
            throws ResorceNotFoundException {
        Personal personal = personalRepository.findById(personalId)
                .orElseThrow(() -> new ResorceNotFoundException("Personal no encontrado por Id : " + personalId));
        personalRepository.delete(personal);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
