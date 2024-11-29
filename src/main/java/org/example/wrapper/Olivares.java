package org.example.wrapper;

import org.example.model.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Olivares")
public class Olivares {

    private List<Olivar> listaOlivares = new ArrayList<>();
    
    public Olivares() {}
    
    public Olivares(List<Olivar> lista) {
        this.listaOlivares = lista;
    }

    @XmlElement(name = "Olivar")
    public List<Olivar> getLista() {
        return listaOlivares;
    }

    public void setLista(List<Olivar> lista) {
        this.listaOlivares = lista;
    }

    @Override
    public String toString() {
        return "Olivares{" +
                "listaOlivares=" + listaOlivares +
                '}';
    }
}
