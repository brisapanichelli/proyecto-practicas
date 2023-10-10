package org.ejemplo.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producto")
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Idproducto;
    private float precio;
    private String modelo;
    private String descripcion;
    private String marca;

    public Producto() {
    }

    public Producto(float precio, String modelo, String descripcion, String marca) {
        this.precio = precio;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.marca = marca;
    }
}
