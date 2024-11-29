package org.example.wrapper;

import org.example.model.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Trabajadores")
public class Trabajadores {

    private List<Trabajador> listaTrabajador = new ArrayList<>();
    
    public Trabajadores() {}
    
    public Trabajadores(List<Trabajador> lista) {
        this.listaTrabajador = lista;
    }

    @XmlElement(name = "Trabajador")
    public List<Trabajador> getLista() { return listaTrabajador; }

    public void setLista(List<Trabajador> lista) {
        this.listaTrabajador = lista;
    }

    @Override
    public String toString() {
        return "Trabajadores{" +
                "listaTrabajadores=" + listaTrabajador +
                '}';
    }
}