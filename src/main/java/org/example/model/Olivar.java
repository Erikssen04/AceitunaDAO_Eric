package org.example.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Olivar")
public class Olivar {


    private int idOlivar;


    private String ubicacion;


    private double hectareas;


    private double produccionAnual;

    public Olivar() {}

    public Olivar(int idOlivar, String ubicacion, double hectareas, double produccionAnual) {
        this.idOlivar = idOlivar;
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
    }

    public Olivar(String ubicacion, double hectareas, double produccionAnual) {
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
    }

    @XmlElement(name = "id")
    public int getId() {
        return idOlivar;
    }

    public void setId(int idOlivar) {
        this.idOlivar = idOlivar;
    }

    @XmlElement(name = "ubicacion")
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @XmlElement(name = "hectareas")
    public double getHectareas() {
        return hectareas;
    }

    public void setHectareas(double hectareas) {
        this.hectareas = hectareas;
    }

    @XmlElement(name = "produccionAnual")
    public double getProduccionAnual() {
        return produccionAnual;
    }

    public void setProduccionAnual(double produccionAnual) {
        this.produccionAnual = produccionAnual;
    }

    @Override
    public String toString() {
        return "Olivar{" +
                "idOlivar=" + idOlivar +
                ", ubicacion='" + ubicacion + '\'' +
                ", hectareas=" + hectareas +
                ", produccionAnual=" + produccionAnual +
                '}';
    }
}
