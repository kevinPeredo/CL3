package com.cibertec.edu.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="producto_id")
	Long id;
	
	@Column(name="producto_nombre")
	String nombre;
	
	@Column(name = "producto_descripcion")
	String descripcion;
	
	@Column(name = "producto_fecha_Registro")
	String fechaRegistro;
	
 }
