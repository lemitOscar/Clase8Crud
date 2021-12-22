package com.curso.seccion8.modelEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * Una de las grandes ventajas de JPA es que nos permite manipular la base de datos 
 * a través de objetos, estos objetos son conocidos como Entity, las cuales son clases
 * comunes y corrientes también llamada POJO’s (Plain Old Java Objects), estas clases tiene
 * la particularidad de ser clases que están mapeadas contra una tabla de la base de datos, 
 * dicho mapeo se lleva a cabo generalmente mediante Anotaciones. 
 * 
 *  JPA debe de ser capaz de identificar que clases son Entity para de esta forma poderlas administrar. 
 *  Es aquí donde nace la importancia de la anotación @Entity,
 * */

 /*
 * existen varias validaciones tienes que investigar
 * */

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	@Email
	private String email;

	// esto es un serializador se ocupara despues
	private static final long serialVersionUID = 1L;

	// espesificacion de la fecha en bd en la bd
	// temporal solo indica el formato en que se va guardar la fecha
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date createAt;

	/*
	 * prepersit antes de insertar en la bd
	 * 
	 * @PrePersist public void prePersist() { createAt = new Date(); }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}