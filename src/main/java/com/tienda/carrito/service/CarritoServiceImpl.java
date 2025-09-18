package com.tienda.carrito.service;

import com.tienda.carrito.model.ItemCarrito;
import com.tienda.carrito.model.Producto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CarritoServiceImpl implements CarritoService {
    
    private final Map<Long, ItemCarrito> carrito = new HashMap<>();
    private final List<Producto> catalogo = Arrays.asList(
            new Producto(1L, "Laptop", 2500.0),
            new Producto(2L, "Mouse", 50.0),
            new Producto(3L, "Teclado", 120.0),
            new Producto(4L, "Monitor", 800.0)
    );

    public List<Producto> listarProductos() {
        return catalogo;
    }

    public void agregarProducto(Long id) {
        Producto producto = catalogo.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (producto != null) {
            carrito.compute(id, (k, v) -> {
                if (v == null) return new ItemCarrito(producto, 1);
                v.incrementarCantidad();
                return v;
            });
        }
    }

    public List<ItemCarrito> verCarrito() {
        return new ArrayList<>(carrito.values());
    }

    public double calcularTotal() {
        return carrito.values().stream().mapToDouble(ItemCarrito::getTotal).sum();
    }

    public void limpiarCarrito() {
        carrito.clear();
    }

    public void eliminarProducto(Long idProducto) {
        carrito.remove(idProducto);
    }

    public void incrementarCantidad(Long idProducto) {
        ItemCarrito item = carrito.get(idProducto);
        if (item != null) {
            item.incrementarCantidad();
        }
    }

    public void decrementarCantidad(Long idProducto) {
        ItemCarrito item = carrito.get(idProducto);
        if (item != null) {
            item.decrementarCantidad();
            if (item.getCantidad() <= 0) {
                carrito.remove(idProducto); // Elimina si la cantidad llega a 0
            }
        }
    }


}