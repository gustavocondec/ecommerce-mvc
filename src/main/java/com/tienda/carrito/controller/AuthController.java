package com.tienda.carrito.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

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
                                HttpSession session) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return "redirect:/login?error"; // credenciales inválidas, vuelve al login con mensaje
        }

        session.setAttribute("userEmail", username.trim());
        return "redirect:/"; // credenciales válidas (sencillo), redirige al catálogo
    }

    @PostMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("userEmail");
        return "redirect:/login?logout";
    }
}
