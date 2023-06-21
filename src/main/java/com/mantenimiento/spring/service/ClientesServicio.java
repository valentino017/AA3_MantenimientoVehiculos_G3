package com.mantenimiento.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mantenimiento.spring.models.Cliente;
import com.mantenimiento.spring.repositorio.ClienteRepositorio;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientesServicio {
    @Autowired
    private ClienteRepositorio repositorio;

    public ClientesServicio() {

    }

    public List<Cliente> buscarTodo() {
        return (List<Cliente>) repositorio.findAll();
    }

    public Cliente crear(Cliente cliente) {
        return repositorio.save(cliente);
    }

    public Cliente actualizar(Cliente clienteActualizar) {
        Cliente clienteActual = repositorio.findById(clienteActualizar.getId_cliente()).get();

        clienteActual.setId_cliente(clienteActualizar.getId_cliente());
        clienteActual.setNombre(clienteActualizar.getNombre());
        clienteActual.setApellido(clienteActualizar.getApellido());
        clienteActual.setTelefono(clienteActualizar.getTelefono());
        clienteActual.setCorreo(clienteActualizar.getCorreo());
        clienteActual.setDireccion(clienteActualizar.getDireccion());

        // Pelicula peliculaActualizado = repositorio.save(peliculaActual);
        Cliente clienteActualizado = repositorio.save(clienteActual);
        return clienteActualizado;

    }

    public Cliente buscarPorId(Integer id) {
        return repositorio.findById(id).get();
    }

    public void borrarPorId(Integer id) {
        repositorio.deleteById(id);
    }

}
