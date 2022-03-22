package org.rnieto.Ahorcado.controller;

import org.rnieto.Ahorcado.model.PartidaPOJO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("juego")
public class PartidaController {
		    @GetMapping("partida")
		    public ModelAndView devuelveAhorcado(HttpServletRequest solicitud) throws IOException {
		        ModelAndView model = new ModelAndView();
		        
		        PartidaPOJO juego;
		        
		        if (solicitud.getSession().getAttribute("juego") != null) {
		        	juego = (PartidaPOJO) solicitud.getSession().getAttribute("juego");
		        } else {
		        	juego = new PartidaPOJO();
		            solicitud.getSession().setAttribute("juego", juego);
		        }
		        
		        if (juego.getPalabra().equals(String.valueOf(juego.getArrayPalabra()))) {
		        	model.addObject("resultado", "Partida ganada!");
		        } else {
		        	model.addObject("resultado", "Partida en curso");
		        }
		        
		        
		        if (juego.getNumFallos() <= 0) {
		        	model.addObject("resultado", "Partida perdida...");
		        }
		        model.addObject("palabraJuego", juego.getArrayPalabra());
		        model.addObject("palabraOculta", juego.getPalabra());
		        model.addObject("numIntentos", juego.getNumIntentos());
		        model.addObject("numFallos", juego.getNumFallos());
		        model.addObject("listaIteraImagen",juego.getListaIteraImagen());
		        
		        model.setViewName("partida");
		        return model;
		    }

		    @PostMapping("partida")
		    public ModelAndView procesaAhorcado(HttpServletRequest solicitud, String letra) {
		        ModelAndView model = new ModelAndView();
		        PartidaPOJO juego;
		        
		        
		        if(!letra.isBlank()) {
			        juego = (PartidaPOJO) solicitud.getSession().getAttribute("juego");
			        juego.compruebaPalabra(letra);
			        
			        solicitud.getSession().setAttribute("juego", juego);
			        
			        model.setViewName("redirect:partida");
		        }
		   
	        	  model.setViewName("redirect:partida");
		        return model;
		    }

		    @PostMapping("borraPartida")
		    public ModelAndView partidaNueva(HttpServletRequest solicitud) {
		        ModelAndView model = new ModelAndView();
		        solicitud.getSession().invalidate();
		        model.setViewName("redirect:partida");
		        return model;
		    }
}
