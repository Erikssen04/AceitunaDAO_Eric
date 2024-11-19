package org.example.model;

public class Cuadrilla {
    private int idCuadrilla;
    private String nombre;
    private int supervisor_id;

    public Cuadrilla(int idCuadrilla, String nombre, int supervisor_id) {
        this.idCuadrilla = idCuadrilla;
        this.nombre = nombre;
        this.supervisor_id = supervisor_id;
    }

    public Cuadrilla(String nombre, int supervisor_id) {
        this.nombre = nombre;
        this.supervisor_id = supervisor_id;
    }

    public int getId() {
        return idCuadrilla;
    }

    public void setId(int idCuadrilla) {
        this.idCuadrilla = idCuadrilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    @Override
    public String toString() {
        return "Cuadrilla{" +
                "idCuadrilla=" + idCuadrilla +
                ", nombre='" + nombre + '\'' +
                ", supervisor_id=" + supervisor_id +
                '}';
    }
}
