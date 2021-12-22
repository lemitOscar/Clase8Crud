package com.curso.seccion8.dao;


import com.curso.seccion8.modelEntity.Cliente;

import org.springframework.data.repository.CrudRepository;

/*
 * Que es un DAO?
 * Data Access Object 
 * es una clase que implementa y provee una interfaz comun para acceder y trabajar con los datos
 * independientemente de las tecnologias a utlilizar como jdbc,jpa,hibernate,toplink,openjpa etc
 * Pero que hace esta inerfaz?
 * esta inerfaz tiene que tener los metodos necesarios para recuperar y almacenar los datos(contrato de implementacion)
 * con las operaciones basicas : crud y otras mas etc
 * */
public interface IClienteDao extends CrudRepository<Cliente,Long>{




    //todo esto se puede generar de manera automatica
    // public List<Cliente> buscarTodos();

    // // guardar usuario
    // public void guardar(Cliente cliente);

    // // buscar cliente por id
    // public Cliente findOne(Long id);

    // // eliminar
    // public void delete(Long id);
}
