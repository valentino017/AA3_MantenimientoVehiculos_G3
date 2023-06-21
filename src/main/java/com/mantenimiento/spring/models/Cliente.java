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
@Table(name = "cliente")
@NamedQueries({
        @NamedQuery(name = "Cliente.findAll", query = "SELECT p FROM  Cliente p"),
        @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT p FROM Cliente p WHERE p.id_cliente = :id_cliente"),
        @NamedQuery(name = "Cliente.findByNombre", query = "SELECT p FROM Cliente p WHERE p.nombre = :nombre"),
        @NamedQuery(name = "Cliente.findByApellido", query = "SELECT p FROM Cliente p WHERE p.apellido = :apellido"),
        @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT p FROM Cliente p WHERE p.telefono = :telefono"),
        @NamedQuery(name = "Cliente.findByCorreo", query = "SELECT p FROM Cliente p WHERE p.correo = :correo"),
        @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT p FROM Cliente p WHERE p.direccion = :direccion") })
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    private Integer id_cliente;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

}
