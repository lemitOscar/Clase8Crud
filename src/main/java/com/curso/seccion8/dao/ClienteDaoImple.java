package com.curso.seccion8.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.curso.seccion8.modelEntity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/*
 * Que es un DAO?
 * Data Access Object 
 * es una clase que implementa y provee una interfaz comun para acceder y trabajar con los datos
 * independientemente de las tecnologias a utlilizar como jdbc,jpa,hibernate,toplink,openjpa etc
 * Pero que hace esta inerfaz?
 * esta inerfaz tiene que tener los metodos necesarios para recuperar y almacenar los datos(contrato de implementacion)
 * con las operaciones basicas : crud y otras mas etc
 * */

/*
 * repository: Es el package que contiene las interfaces que extienden de JPA para que estas
 *  clases se conecten a la base de datos.
 *  Estas gestionan información ya sea de buscar, borrar, actualizar o crear un registro en la base de datos.
 * */

@Repository
public class ClienteDaoImple implements IClienteDao {
	
	//se encarga de manegar las entidades en pocas palabras es el chalan
    @PersistenceContext
    private EntityManager entityManager;
    
    
    //listar clientes
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> buscarTodos() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    
    
    //Guardar clientes
    @Override
    @Transactional
    public void guardar(Cliente cliente) {
       entityManager.persist(cliente);
    }
}
