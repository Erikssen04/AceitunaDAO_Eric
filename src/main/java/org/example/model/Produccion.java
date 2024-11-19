package org.example.model;

public class Produccion {
    private int idProduccion;
    private int cuadrillaId;
    private int olivarId;
    private int almazaraId;
    private String fecha;
    private double cantidadRecolectada;

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

    public int getId() {
        return idProduccion;
    }

    public void setId(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public int getCuadrillaId() {
        return cuadrillaId;
    }

    public void setCuadrillaId(int cuadrillaId) {
        this.cuadrillaId = cuadrillaId;
    }

    public int getOlivarId() {
        return olivarId;
    }

    public void setOlivarId(int olivarId) {
        this.olivarId = olivarId;
    }

    public int getAlmazaraId() {
        return almazaraId;
    }

    public void setAlmazaraId(int almazaraId) {
        this.almazaraId = almazaraId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

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
