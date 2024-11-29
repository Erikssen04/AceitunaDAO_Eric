package org.example.wrapper;

import org.example.model.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement (name = "Almazaras")
public class Almazaras {

    private List<Almazara> listaAlmazaras = new ArrayList<>();

    public Almazaras() {}

    public Almazaras(List<Almazara> lista) {
        this.listaAlmazaras = lista;
    }

    @XmlElement(name = "Almazara")
    public List<Almazara> getLista() {
        return listaAlmazaras;
    }

    public void setLista(List<Almazara> lista) {
        this.listaAlmazaras = lista;
    }

    @Override
    public String toString() {
        return "Almazaras{" +
                "listaCuadrillas=" + listaAlmazaras +
                '}';
    }
}
