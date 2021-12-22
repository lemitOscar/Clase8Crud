package com.curso.seccion8.controller;

import com.curso.seccion8.modelEntity.Cliente;
import com.curso.seccion8.service.IclienteService;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	private static Logger logMymessage = LoggerFactory.getLogger(ClienteController.class);

	// cuando llegue a tener varias implemetnaciones hay que usar qualifire
	// inyection of dependence
	@Autowired
	private IclienteService iclienteService;

	// index de la aplicacion web
	//este es el listar
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "lista de clientes");
		model.addAttribute("clientes", iclienteService.buscarTodos());
		return "lista";
	}

	// formulario para registrar clientes
	@RequestMapping(value = "/form")
	public String formula(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		return "form";
	}

	// editar usuarios
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = iclienteService.findOne(id);
		} else {
			return "redirect:/";
		}
		model.put("cliente", cliente);
		return "form";
	}

	//guardar cliente
	// el orden para validar es muy importante sino es asi da error
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String insertFormClient(@Valid Cliente cliente, BindingResult bindingResult, SessionStatus status) {

		if (bindingResult.hasErrors()) {
			return "form";
		}

		iclienteService.guardar(cliente);
		status.setComplete();
		return "redirect:/";
	}

	// eliminar usuario}
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			iclienteService.delete(id);
		}
		return "redirect:/";
	}
}
