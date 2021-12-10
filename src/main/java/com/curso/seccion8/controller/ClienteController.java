package com.curso.seccion8.controller;

import com.curso.seccion8.dao.IClienteDao;
import com.curso.seccion8.modelEntity.Cliente;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ClienteController {
	
	private static  Logger logMymessage = LoggerFactory.getLogger(ClienteController.class);

	// cuando llegue a tener varias implemetnaciones hay que usar qualifire
	// inyection of dependence
	@Autowired
	private IClienteDao clienteDao;

	// index de la aplicacion web
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "lista de clientes");
		model.addAttribute("clientes", clienteDao.buscarTodos());
		return "lista";
	}

	// formulario para registrar clientes
	@RequestMapping(value = "/form")
	public String formula(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);

		return "form";
	}
	
	//el orden para validar es muy importante sino es asi da error
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String insertFormClient(@Valid  Cliente cliente, BindingResult bindingResult) {
		logMymessage.info("el resultado es "+bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			return "form";
		}

		clienteDao.guardar(cliente);
		return "redirect:/";
	}

}
