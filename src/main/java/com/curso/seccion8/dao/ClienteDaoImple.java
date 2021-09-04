package com.curso.seccion8.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.curso.seccion8.modelEntity.Cliente;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClienteDaoImple implements IClienteDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> buscarTodos() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
       entityManager.persist(cliente);
    }
}
