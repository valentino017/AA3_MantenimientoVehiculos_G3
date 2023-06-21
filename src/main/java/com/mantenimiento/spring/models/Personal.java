package com.mantenimiento.spring.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal")
@NamedQueries({
        @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM  Personal p"),
        @NamedQuery(name = "Personal.findByIdPersonal", query = "SELECT p FROM Personal p WHERE p.id_personal = :id_personal"),
        @NamedQuery(name = "Personal.findByNombre", query = "SELECT p FROM Personal p WHERE p.nombre = :nombre"),
        @NamedQuery(name = "Personal.findByIdSede", query = "SELECT p FROM Personal p WHERE p.id_sede= :id_sede"),
        @NamedQuery(name = "Personal.findByApellido", query = "SELECT p FROM Personal p WHERE p.apellido= :apellido"),
        @NamedQuery(name = "Personal.findByCargo", query = "SELECT p FROM Personal p WHERE p.cargo = :cargo"),
        @NamedQuery(name = "Personal.findByTurno", query = "SELECT p FROM Personal p WHERE p.turno = :turno")
})

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    private Integer id_personal;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "id_sede")
    private Integer id_sede;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "turno")
    private String turno;

    
}
