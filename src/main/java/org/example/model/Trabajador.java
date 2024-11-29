package org.example.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Trabajadores")
public class Trabajador {


    private int idTrabajador;


    private String nombre;


    private int edad;


    private String puesto;


    private double salario;

    public Trabajador() {}

    public Trabajador(int idTrabajador, String nombre, int edad, String puesto, double salario) {
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
    }

    public Trabajador(String nombre, int edad, String puesto, double salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
    }

    @XmlElement(name = "id")
    public int getId() {
        return idTrabajador;
    }

    public void setId(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "edad")
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @XmlElement(name = "puesto")
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @XmlElement(name = "salario")
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "idTrabajador=" + idTrabajador +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario +
                '}';
    }
}
