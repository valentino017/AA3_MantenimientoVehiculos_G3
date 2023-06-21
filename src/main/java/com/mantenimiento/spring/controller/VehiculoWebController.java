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

import com.mantenimiento.spring.models.Vehiculo;
import com.mantenimiento.spring.service.VehiculosServicio;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoWebController {
	
	@Autowired
	private VehiculosServicio servicio;
	
	@RequestMapping("/listarTodo")
    private String listarVehiculos(Model model) {
        List<Vehiculo> listaVehiculos = servicio.buscarTodo();
        System.out.println("Lista de Vehiculos :" + listaVehiculos);
        model.addAttribute("listaVehiculos", listaVehiculos);
        return "/moduloVehiculos/listarTodo";
    }

    @RequestMapping("/nuevo")
    public String nuevoVehiculo(Model model) {
    	Vehiculo vehiculo = new Vehiculo();
        model.addAttribute("vehiculo", vehiculo);
        
        // nuevo por nueva
        return "/moduloVehiculos/nuevoVehiculo";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crearVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        servicio.crear(vehiculo);
        return "redirect:/vehiculos/listarTodo";  
    }

    @RequestMapping(value = "/actualizar/{id}")
    public ModelAndView editarVehiculo(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloVehiculos/editarVehiculo");
        Vehiculo vehiculo = servicio.buscarPorId(id);
        //"sede" por "Sede"
        mav.addObject("vehiculo", vehiculo);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}")   
    public String eliminarVehiculo(@PathVariable(name = "id") int id) {
        servicio.borrarPorId(id);
        return "redirect:/vehiculos/listarTodo";
    }

}
