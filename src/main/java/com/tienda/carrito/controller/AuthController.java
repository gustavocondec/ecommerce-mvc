package com.tienda.carrito.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tienda.carrito.service.CarritoService;

import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    CarritoService carritoService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute("userEmail") != null) {
            return "redirect:/"; // Usuario logueado vuelve al catálogo
        }
        return "login"; // Renderiza templates/login.html
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return "redirect:/login?error"; // credenciales inválidas, vuelve al login con mensaje
        }

        session.setAttribute("userEmail", username.trim());
        //return "redirect:/catalogo"; // credenciales válidas (sencillo), redirige al catálogo
        model.addAttribute("productos", carritoService.listarProductos());
        return "catalogo";
    }

    @PostMapping("/logout")
    public String cerrarSesion(HttpSession session) {

        session.removeAttribute("userEmail");
        session.removeAttribute("productos");
        session.removeAttribute("items");
        session.removeAttribute("total");
        carritoService.limpiarCarrito();
        return "redirect:/login?logout";
    }
}
