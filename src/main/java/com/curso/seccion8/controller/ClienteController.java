package com.curso.seccion8.controller;

import com.curso.seccion8.modelEntity.Cliente;
import com.curso.seccion8.page_render.PageRender;
import com.curso.seccion8.service.IclienteService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    private static Logger logMymessage = LoggerFactory.getLogger(ClienteController.class);

    // cuando llegue a tener varias implemetnaciones hay que usar qualifire
    // inyection of dependence

    @Autowired
    private IclienteService clienteService;

    // index de la aplicacion web
    // este es el listar con paginador

    // @RequestMapping(value = "/", method = RequestMethod.GET)
    // public String listar(Model model) {
    // model.addAttribute("titulo", "lista de clientes");
    // model.addAttribute("clientes", clienteService.buscarTodos());
    // return "lista";
    // }

    // ejemplo con paginacion
    @GetMapping(value = "/usuarios")
    public String conPaginador(Model model, Pageable page) {
        Page<Cliente> cliente = clienteService.buscarTodo(page);
        model.addAttribute("clientes", cliente);
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
            cliente = clienteService.findOne(id);
        } else {
            return "redirect:/";
        }
        model.put("cliente", cliente);
        return "form";
    }

    // guardar cliente
    // el orden para validar es muy importante sino es asi da error
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String insertFormClient(@Valid Cliente cliente, BindingResult bindingResult, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        if (!foto.isEmpty()) {
            //validar para actualizar foto
            if (cliente.getId()!=null && cliente.getFoto()!=null){
                Path rooPath = Paths.get("/home/oslim/testImg").resolve(cliente.getFoto());
                File archivo = rooPath.toFile();
                if (archivo.exists() && archivo.canRead()) {
                    if (archivo.delete()) {
                        logMymessage.info("si lo borre papu");
                    }
                }
            }

            String rootPath = "/home/oslim/testImg";
            try {
                byte[] bytes = foto.getBytes();
                String nombreUnico = UUID.randomUUID().toString()+"_"+foto.getOriginalFilename();
                Path rutacom = Paths.get(rootPath + "//" +nombreUnico);
                Files.write(rutacom, bytes);
                cliente.setFoto(nombreUnico);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clienteService.guardar(cliente);
        status.setComplete();
        flash.addFlashAttribute("success", "cliente creado con exito");
        return "redirect:/usuarios";
    }

    // eliminar usuario}
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);
            clienteService.delete(id);
            Path rooPath = Paths.get("/home/oslim/testImg").resolve(cliente.getFoto());
            File archivo = rooPath.toFile();
            if (archivo.exists() && archivo.canRead()) {
                if (archivo.delete()) {
                    logMymessage.info("si lo borre papu");
                    return "redirect:/usuarios";
                }
            }
            flash.addFlashAttribute("success", "cliente fue eliminado con exito");
        }
        return "redirect:/usuarios";

    }

    //ctrl+alt+l
    //detalle del usuario
    @GetMapping(value = "/ver/{id}")
    public String detallesUsuario(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Cliente cliente = clienteService.findOne(id);
        if (cliente == null) {
            return "redirect:/usuarios";
        }
        model.put("cliente", cliente);
        return "ver";
    }
}
