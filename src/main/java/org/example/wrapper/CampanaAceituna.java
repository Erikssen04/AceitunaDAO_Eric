package org.example.wrapper;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Campana_Aceituna")
public class CampanaAceituna {

    private Almazaras almazaras;

    private Cuadrillas cuadrillas;

    private Olivares olivares;

    private Producciones producciones;

    private Trabajadores trabajadores;

    public CampanaAceituna() {}

    public CampanaAceituna(Almazaras almazaras, Cuadrillas cuadrillas, Olivares olivares, Producciones producciones, Trabajadores trabajadores) {
        this.almazaras = almazaras;
        this.cuadrillas = cuadrillas;
        this.olivares = olivares;
        this.producciones = producciones;
        this.trabajadores = trabajadores;
    }

    public void setAlmazaras(Almazaras almazaras) {
        this.almazaras = almazaras;
    }

    public void setCuadrillas(Cuadrillas cuadrillas) {
        this.cuadrillas = cuadrillas;
    }

    public void setOlivares(Olivares olivares) {
        this.olivares = olivares;
    }

    public void setProducciones(Producciones producciones) {
        this.producciones = producciones;
    }

    public void setTrabajadores(Trabajadores trabajadores) {
        this.trabajadores = trabajadores;
    }

    @XmlElement(name = "Almazaras")
    public Almazaras getAlmazaras() {
        return almazaras;
    }

    @XmlElement(name = "Cuadrillas")
    public Cuadrillas getCuadrillas() {
        return cuadrillas;
    }

    @XmlElement(name = "Olivares")
    public Olivares getOlivares() {
        return olivares;
    }

    @XmlElement(name = "Producciones")
    public Producciones getProducciones() {
        return producciones;
    }

    @XmlElement(name = "Tranajadores")
    public Trabajadores getTrabajadores() {
        return trabajadores;
    }

    @Override
    public String toString() {
        return "CampanaAceituna{" +
                "almazaras=" + almazaras +
                ", cuadrillas=" + cuadrillas +
                ", olivares=" + olivares +
                ", producciones=" + producciones +
                ", trabajadores=" + trabajadores +
                '}';
    }
}