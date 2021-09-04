package com.curso.seccion8.dao;

import java.util.List;

import com.curso.seccion8.modelEntity.Cliente;

public interface IClienteDao {
    public List<Cliente> buscarTodos();

    //guardar usuario
    public void guardar(Cliente cliente);
}
