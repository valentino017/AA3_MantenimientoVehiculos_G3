package com.mantenimiento.spring.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sede")
@NamedQueries({
		@NamedQuery(name = "Sede.findAll", query = "SELECT p FROM  Sede p"),
		@NamedQuery(name = "Sede.findById_sede", query = "SELECT p FROM  Sede p WHERE p.id_sede = :id_sede"),
		@NamedQuery(name = "Sede.findByNombre", query = "SELECT p FROM  Sede p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "Sede.findByDireccion", query = "SELECT p FROM  Sede p WHERE p.direccion = :direccion"),
		@NamedQuery(name = "Sede.findById_personal", query = "SELECT p FROM  Sede p WHERE p.id_personal = :id_personal") })

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Sede implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = true)
	private Integer id_sede;

	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "id_personal")
	private Integer id_personal;

}
