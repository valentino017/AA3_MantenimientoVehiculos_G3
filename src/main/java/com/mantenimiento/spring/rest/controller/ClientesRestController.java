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
import com.mantenimiento.spring.models.Cliente;
import com.mantenimiento.spring.models.exceptions.ResorceNotFoundException;
import com.mantenimiento.spring.repositorio.ClienteRepositorio;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/api/clientes")
public class ClientesRestController {

    @Autowired
    private ClienteRepositorio clienteRepository;

    @GetMapping()
    public List<Cliente> getAllclientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteId(@PathVariable(value = "id") Integer clienteId)
            throws ResorceNotFoundException {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResorceNotFoundException("Cliente no encontrado por Id: " + clienteId));
        return ResponseEntity.ok(cliente);

    }

    @PostMapping()
    public Cliente createCliente(@Validated @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable(value = "id") Integer clienteId,
            @Validated @RequestBody Cliente clienteDetails) throws ResorceNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResorceNotFoundException("Cliente no encontrado por Id : " + clienteId));

        // cliente.setId_cliente(clienteDetails.getId_cliente());
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setApellido(clienteDetails.getApellido());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setCorreo(clienteDetails.getCorreo());
        cliente.setDireccion(clienteDetails.getDireccion());

        final Cliente updateCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(updateCliente);

    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCliente(@PathVariable(value = "id") Integer clienteId)
            throws ResorceNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResorceNotFoundException("Cliente no encontrado por Id : " + clienteId));
        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

}
