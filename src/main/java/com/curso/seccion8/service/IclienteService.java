package com.curso.seccion8.service;

import java.util.List;

import com.curso.seccion8.modelEntity.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IclienteService {
    public List<Cliente> buscarTodos();

    public Page<Cliente> buscarTodo(Pageable page);

    // guardar usuario
    public void guardar(Cliente cliente);

    // buscar cliente por id
    public Cliente findOne(Long id);

    // eliminar
    public void delete(Long id);
}
