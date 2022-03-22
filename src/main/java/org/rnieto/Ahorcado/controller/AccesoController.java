package org.rnieto.Ahorcado.controller;

import jdk.jshell.execution.Util;

import org.rnieto.Ahorcado.model.UserDTO;
import org.rnieto.Ahorcado.util.Metodos;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequestMapping("/acceso")
public class AccesoController {
    public static boolean crearRoot = true;

    @GetMapping("login")
    public ModelAndView devuelveFormularioLogin(HttpServletRequest solicitud) throws UnknownHostException {
        ModelAndView model = new ModelAndView();
        
        //Creamos el usuario root para el login
        if (crearRoot) {
            Metodos.creaUsuario();
        }
        //Lo ponemos a false para volver a crearlo si volvemos a entrar
        crearRoot = false;
        UserDTO userDTO;
        //Cogemos la ip
        String ip = InetAddress.getLocalHost().getHostAddress();
        //Cogemos de la cabecera http el atributo correspondinete a la info del navegador
        String info_navegador = solicitud.getHeader("user-agent");
        //Si no existe en la sesion lo creamos
        if (solicitud.getSession().getAttribute("userDTO") != null) {
        	userDTO = (UserDTO) solicitud.getSession().getAttribute("userDTO");
        } else {
        	userDTO = new UserDTO();
        }
        //Añadimos todo
        model.addObject("userDTO", userDTO);
        model.addObject("direccionIP", ip);
  
        model.addObject("info_navegador", info_navegador);
        model.setViewName("login");
        return model;
    }

    @PostMapping("login")
    public ModelAndView recibeCredencialesLogin(@Valid UserDTO userDTO, BindingResult bindingr,
                                                HttpServletRequest solicitud) throws UnknownHostException {
        ModelAndView model = new ModelAndView();
        
        String ip = InetAddress.getLocalHost().getHostAddress();

        String info_navegador = solicitud.getHeader("user-agent");
        boolean partida = false;
        
        
        if (bindingr.hasErrors()) {
        	model.addObject("otroError", "No se ha encontrado un usuario con esos datos");
        	model.addObject("direccionIP", ip);
  
        	model.addObject("info_navegador", info_navegador);
        	model.addObject("userDTO", userDTO);
        	model.setViewName("login");
        } else {
            if (userDTO.getUsuario().equals(Metodos.root.getUsuario()) && userDTO.getClave().equals(Metodos.root.getClave())) {
                partida = true;
            }
            if (partida) {
                model.setViewName("redirect:/juego/partida");
            } else {
            	model.addObject("otroError", "No se encuentra ningun usuario con los campos dados");
            	model.addObject("direccionIP", ip);
            	model.addObject("info_navegador", info_navegador);
            	model.addObject("userDTO", userDTO);
            	model.setViewName("login");
            }
        }
        return model;
    }


}
