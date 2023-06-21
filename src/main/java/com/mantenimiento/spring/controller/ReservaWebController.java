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

import com.mantenimiento.spring.models.Reserva;
import com.mantenimiento.spring.service.ReservasServicio;

@Controller
@RequestMapping("/reservas")
public class ReservaWebController {

    @Autowired
    private ReservasServicio servicio;

    @RequestMapping("/listarTodo")
    private String listarReservas(Model model) {
        List<Reserva> listaReservas = servicio.buscarTodo();
        System.out.println("Lista de Reservas :" + listaReservas);
        model.addAttribute("listaReservas", listaReservas);
        return "/moduloReservas/listarTodo";
    }

    @RequestMapping("/nuevo")
    public String nuevoReserva(Model model) {
        Reserva reserva = new Reserva();
        model.addAttribute("reserva", reserva);
        return "/moduloReservas/nuevaReserva";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String crearReserva(@ModelAttribute("reserva") Reserva reserva) {
        servicio.crear(reserva);
        return "redirect:/reservas/listarTodo";
    }

    @RequestMapping(value = "/actualizar/{id}")
    public ModelAndView editarReserva(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloReservas/editarReserva");
        Reserva reserva = servicio.buscarPorId(id);
        mav.addObject("reserva", reserva);
        return mav;
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminarReserva(@PathVariable(name = "id") int id) {
        servicio.borrarPorId(id);
        return "redirect:/reservas/listarTodo";
    }
}
