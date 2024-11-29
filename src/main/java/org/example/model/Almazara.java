package org.example.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Almazara")
public class Almazara {

    private int idAlmazara;


    private String nombre;


    private String ubicacion;


    private double capacidad;


    public Almazara() {}

    public Almazara(int idAlmazara, String nombre, String ubicacion, double capacidad) {
        this.idAlmazara = idAlmazara;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    public Almazara(String nombre, String ubicacion, double capacidad) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
    }

    @XmlElement(name = "id")
    public int getId() {
        return idAlmazara;
    }

    public void setId(int idAlmazara) {
        this.idAlmazara = idAlmazara;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "ubicacion")
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @XmlElement(name = "capacidad")
    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Almazara{" +
                "idAlmazara=" + idAlmazara +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
