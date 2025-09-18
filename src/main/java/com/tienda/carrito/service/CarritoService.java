package com.tienda.carrito.service;

import java.util.List;

import com.tienda.carrito.model.ItemCarrito;
import com.tienda.carrito.model.Producto;

public interface CarritoService {

    public List<Producto> listarProductos();

    public void agregarProducto(Long id);

    public List<ItemCarrito> verCarrito();

    public double calcularTotal();

    public void limpiarCarrito();

    public void eliminarProducto(Long idProducto);

    public void incrementarCantidad(Long idProducto);

    public void decrementarCantidad(Long idProducto);
    
}
