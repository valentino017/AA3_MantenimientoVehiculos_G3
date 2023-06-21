package com.mantenimiento.spring.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "reserva")
@NamedQueries({
        @NamedQuery(name = "Reserva.findAll", query = "SELECT p FROM  Reserva p"),
        @NamedQuery(name = "Reserva.findByIdReserva", query = "SELECT p FROM Reserva p WHERE p.id_reserva = :id_reserva"),
        @NamedQuery(name = "Reserva.findByIdCliente", query = "SELECT p FROM Reserva p WHERE p.id_cliente = :id_cliente"),
        @NamedQuery(name = "Reserva.findByIdSede", query = "SELECT p FROM Reserva p WHERE p.id_sede = :id_sede"),
        @NamedQuery(name = "Reserva.findByIdVehiculo", query = "SELECT p FROM Reserva p WHERE p.id_vehiculo = :id_vehiculo"),
        @NamedQuery(name = "Reserva.findByFechaIngreso", query = "SELECT p FROM Reserva p WHERE p.fechaIngreso = :fechaIngreso"),
        @NamedQuery(name = "Reserva.findByTurno", query = "SELECT p FROM Reserva p WHERE p.Turno = :Turno") })
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Reserva  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    private Integer id_reserva;

    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer id_cliente;

    @Column(name = "id_sede")
    private Integer id_sede;

    @Column(name = "id_vehiculo")
    private Integer id_vehiculo;

    @Column(name = "fechaIngreso")
    private String fechaIngreso;

    @Column(name = "Turno")
    private String Turno;

}
