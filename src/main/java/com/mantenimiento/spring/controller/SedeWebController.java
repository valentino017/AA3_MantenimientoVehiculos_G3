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

import com.mantenimiento.spring.models.Sede;
import com.mantenimiento.spring.service.SedesServicio;



@Controller
@RequestMapping("/sedes")
public class SedeWebController {
	
	@Autowired
    private SedesServicio servicio;

    @RequestMapping("/listarTodo")
    private String listarSedes(Model model) {
        List<Sede> listaSedes = servicio.buscarTodo();
        System.out.println("Lista de Sedes :" + listaSedes);
        model.addAttribute("listaSedes", listaSedes);
        return "/moduloSedes/listarTodo";
    }

    @RequestMapping("/nuevo")
    public String nuevoSede(Model model) {
        Sede sede = new Sede();
        model.addAttribute("sede", sede);
        
        // nuevo por nueva
        return "/moduloSedes/nuevoSede";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crearSede(@ModelAttribute("sede") Sede sede) {
        servicio.crear(sede);
        return "redirect:/sedes/listarTodo";  
    }

    @RequestMapping(value = "/actualizar/{id}")
    public ModelAndView editarSede(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloSedes/editarSede");
        Sede sede = servicio.buscarPorId(id);
        //"sede" por "Sede"
        mav.addObject("sede", sede);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}")   
    public String eliminarSede(@PathVariable(name = "id") int id) {
        servicio.borrarPorId(id);
        return "redirect:/sedes/listarTodo";
    }

}
