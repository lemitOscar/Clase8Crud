package com.curso.seccion8.service;

import java.util.List;

import com.curso.seccion8.dao.IClienteDao;
import com.curso.seccion8.modelEntity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements IclienteService {

    @Autowired
    IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteDao.findById(id).orElseGet(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Cliente> buscarTodo(Pageable page) {
        return clienteDao.findAll(page);
    }

}
