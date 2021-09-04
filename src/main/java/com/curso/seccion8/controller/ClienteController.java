package com.curso.seccion8.controller;



import com.curso.seccion8.dao.IClienteDao;
import com.curso.seccion8.modelEntity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {

    @Autowired
    private IClienteDao clienteDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "lista de clientes");
        model.addAttribute("clientes", clienteDao.buscarTodos());
        return "lista";
    }

    @RequestMapping(value = "/form")
    public String formula(Model model){
        model.addAttribute("titulo", "formulario cliente");
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "formulario";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String insertFormClient(Cliente cliente){
        clienteDao.guardar(cliente);
        return "redirect: lista";
    }

}
