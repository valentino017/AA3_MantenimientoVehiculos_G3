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

import com.mantenimiento.spring.models.Personal;
import com.mantenimiento.spring.service.PersonalServicio;

@Controller
@RequestMapping("/personal")

public class PersonalWebController {

    @Autowired
    private PersonalServicio servicio;
    
    @RequestMapping("/listarTodo")
    private String listarPersonal(Model model) {
        List<Personal> listaPersonal = servicio.buscarTodo();
        System.out.println("Lista de Personal :" + listaPersonal);
        model.addAttribute("listaPersonal", listaPersonal);
        return "/moduloPersonal/listarTodo";
    }
    
    @RequestMapping("/nuevo")
    public String nuevoPersonal(Model model) {
        Personal personal = new Personal();
        model.addAttribute("personal", personal);
        return "/moduloPersonal/nuevoPersonal";
    }
    
    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crearPersonal(@ModelAttribute("personal") Personal personal) {
        servicio.crear(personal);
        return "redirect:/personal/listarTodo";
    }
    
    @RequestMapping(value = "/actualizar/{id}")
    public ModelAndView editarPersonal(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloPersonal/editarPersonal");
        Personal personal = servicio.buscarPorId(id);
        mav.addObject("personal", personal);
        return mav;
    }
    
    
    @RequestMapping(value = "/eliminar/{id}")   
    public String eliminarPersonal(@PathVariable(name = "id") int id) {
        servicio.borrarPorId(id);
        return "redirect:/personal/listarTodo";
    }
    
}

