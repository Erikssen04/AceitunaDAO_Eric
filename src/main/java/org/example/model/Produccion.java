package org.example.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Produccion")
public class Produccion {


    private int idProduccion;


    private int cuadrillaId;


    private int olivarId;


    private int almazaraId;


    private String fecha;


    private double cantidadRecolectada;

    public Produccion() {}

    public Produccion(int idProduccion, int cuadrillaId, int olivarId, int almazaraId, String fecha, double cantidadRecolectada) {
        this.idProduccion = idProduccion;
        this.cuadrillaId = cuadrillaId;
        this.olivarId = olivarId;
        this.almazaraId = almazaraId;
        this.fecha = fecha;
        this.cantidadRecolectada = cantidadRecolectada;
    }

    public Produccion(int cuadrillaId, int olivarId, int almazaraId, String fecha, double cantidadRecolectada) {
        this.cuadrillaId = cuadrillaId;
        this.olivarId = olivarId;
        this.almazaraId = almazaraId;
        this.fecha = fecha;
        this.cantidadRecolectada = cantidadRecolectada;
    }

    @XmlElement(name = "id")
    public int getId() {
        return idProduccion;
    }

    public void setId(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    @XmlElement(name = "ID_cuadrilla")
    public int getCuadrillaId() {
        return cuadrillaId;
    }

    public void setCuadrillaId(int cuadrillaId) {
        this.cuadrillaId = cuadrillaId;
    }

    @XmlElement(name = "ID_olivar")
    public int getOlivarId() {
        return olivarId;
    }

    public void setOlivarId(int olivarId) {
        this.olivarId = olivarId;
    }

    @XmlElement(name = "ID_almazara")
    public int getAlmazaraId() {
        return almazaraId;
    }

    public void setAlmazaraId(int almazaraId) {
        this.almazaraId = almazaraId;
    }

    @XmlElement(name = "ID_fecha")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlElement(name = "ID_cantidadRecolectada")
    public double getCantidadRecolectada() {
        return cantidadRecolectada;
    }

    public void setCantidadRecolectada(double cantidadRecolectada) {
        this.cantidadRecolectada = cantidadRecolectada;
    }

    @Override
    public String toString() {
        return "Produccion{" +
                "idProduccion=" + idProduccion +
                ", cuadrillaId=" + cuadrillaId +
                ", olivarId=" + olivarId +
                ", almazaraId=" + almazaraId +
                ", fecha=" + fecha +
                ", cantidadRecolectada=" + cantidadRecolectada +
                '}';
    }
}
