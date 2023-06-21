package com.mantenimiento.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mantenimiento.spring.models.Cliente;
import com.mantenimiento.spring.service.ClientesServicio;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {

    @Autowired
    private ClientesServicio servicio;

    @RequestMapping("/listarTodo")
    private String listarClientes(Model model) {
        List<Cliente> listaClientes = servicio.buscarTodo();
        System.out.println("Lista de Clientes :" + listaClientes);
        model.addAttribute("listaClientes", listaClientes);
        return "/moduloClientes/listarTodo";
    }

    @RequestMapping("/nuevo")
    public String nuevoCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "/moduloClientes/nuevoCliente";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crearCliente(@ModelAttribute("cliente") Cliente cliente) {
        servicio.crear(cliente);
        return "redirect:/clientes/listarTodo";
    }

    @RequestMapping(value = "/actualizar/{id}")
    public ModelAndView editarCliente(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloClientes/editarCliente");
        Cliente cliente = servicio.buscarPorId(id);
        mav.addObject("cliente", cliente);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}")   
    public String eliminarCliente(@PathVariable(name = "id") int id) {
        servicio.borrarPorId(id);
        return "redirect:/clientes/listarTodo";
    }
}
