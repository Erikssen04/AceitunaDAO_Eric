package org.example.wrapper;

import org.example.model.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Producciones")
public class Producciones {

    private List<Produccion> listaProducciones = new ArrayList<>();

    public Producciones() {}

    public Producciones(List<Produccion> lista) {
        this.listaProducciones = lista;
    }

    @XmlElement(name = "Produccion")
    public List<Produccion> getLista() {
        return listaProducciones;
    }

    public void setLista(List<Produccion> lista) {
        this.listaProducciones = lista;
    }

    @Override
    public String toString() {
        return "Producciones{" +
                "listaProducciones=" + listaProducciones +
                '}';
    }
}
