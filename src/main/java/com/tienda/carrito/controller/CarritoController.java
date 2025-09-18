package com.tienda.carrito.controller;

import com.tienda.carrito.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @GetMapping("/")
    public String verCatalogo(Model model) {
        carritoService.limpiarCarrito();
        return "login";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
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

    @PostMapping("/carrito/eliminar")
    public String eliminarProducto(@RequestParam("idProducto") Long idProducto) {
        carritoService.eliminarProducto(idProducto);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/incrementar")
    public String incrementar(@RequestParam("idProducto") Long idProducto) {
        carritoService.incrementarCantidad(idProducto);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/decrementar")
    public String decrementar(@RequestParam("idProducto") Long idProducto) {
        carritoService.decrementarCantidad(idProducto);
        return "redirect:/carrito";
    }

}