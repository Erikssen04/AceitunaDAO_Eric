package org.example.wrapper;

import org.example.model.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Cuadrillas")
public class Cuadrillas {

    private List<Cuadrilla> listaCuadrillas = new ArrayList<>();
    
    public Cuadrillas() {}
    
    public Cuadrillas(List<Cuadrilla> lista) {
        this.listaCuadrillas = lista;
    }

    @XmlElement(name = "Cuadrilla")
    public List<Cuadrilla> getLista() {
        return listaCuadrillas;
    }

    public void setLista(List<Cuadrilla> lista) {
        this.listaCuadrillas = lista;
    }

    @Override
    public String toString() {
        return "Cuadrillas{" +
                "listaCuadrillas=" + listaCuadrillas +
                '}';
    }
}