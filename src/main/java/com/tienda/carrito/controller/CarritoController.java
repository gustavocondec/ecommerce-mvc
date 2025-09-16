package com.tienda.carrito.controller;

import com.tienda.carrito.service.CarritoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/")
    public String verCatalogo(Model model) {
        model.addAttribute("productos", carritoService.listarProductos());
        return "catalogo";
    }

    @PostMapping("/agregar/{id}")
    public String agregarProducto(@PathVariable Long id) {
        carritoService.agregarProducto(id);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        model.addAttribute("items", carritoService.verCarrito());
        model.addAttribute("total", carritoService.calcularTotal());
        return "carrito";
    }

    @PostMapping("/carrito/limpiar")
    public String limpiarCarrito() {
        carritoService.limpiarCarrito();
        return "redirect:/carrito";
    }
}