package com.mantenimiento.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.spring.models.Personal;
import com.mantenimiento.spring.repositorio.PersonalRepositorio;
import javax.transaction.Transactional;

@Service
@Transactional
public class PersonalServicio {
    @Autowired
    private PersonalRepositorio repositorio;

    public PersonalServicio() {

    }

    public List<Personal> buscarTodo() {
        return (List<Personal>) repositorio.findAll();
    }

    public Personal crear(Personal personal) {
        return repositorio.save(personal);
    }

    public Personal actualizar(Personal personalActualizar) {
        Personal personalActual = repositorio.findById(personalActualizar.getId_personal()).get();

        personalActual.setId_personal(personalActualizar.getId_personal());
        personalActual.setNombre(personalActualizar.getNombre());
        personalActual.setNombre(personalActualizar.getApellido());
        personalActual.setId_sede(personalActualizar.getId_sede());
        personalActual.setCargo(personalActualizar.getCargo());
        personalActual.setTurno(personalActualizar.getTurno());

        //
        Personal personalActualizado = repositorio.save(personalActual);
        return personalActualizado;

    }

    public Personal buscarPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    public void borrarPorId(Integer id) {
        repositorio.deleteById(id);

    }

}
