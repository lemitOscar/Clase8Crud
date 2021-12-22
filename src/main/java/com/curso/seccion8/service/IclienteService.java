package com.curso.seccion8.service;

import java.util.List;

import com.curso.seccion8.modelEntity.Cliente;

public interface IclienteService {
    public List<Cliente> buscarTodos();

    // guardar usuario
    public void guardar(Cliente cliente);

    // buscar cliente por id
    public Cliente findOne(Long id);

    // eliminar
    public void delete(Long id);
}
