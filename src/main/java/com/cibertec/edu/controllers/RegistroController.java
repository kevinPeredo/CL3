package com.cibertec.edu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.edu.models.Producto;
import com.cibertec.edu.repositories.ProductoDao;



@Controller
public class RegistroController {

	@Autowired
	private ProductoDao productoRepository;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/Registro")
	public String registrarProducto(@ModelAttribute("productos") Producto productos) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "Registro";
		} else {
			return "Acceso_denegado";
		}
	}
	
	@PostMapping("/registro")
	public String registrarProducto(@Validated @ModelAttribute("productos") Producto productos, BindingResult binding) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			if(binding.hasErrors()) {
				return "registro";
			}
			
			productoRepository.save(productos);
			
			return "redirect:/";
			
		} else {
			return "Acceso_denegado";
		}
	}

}
