package org.example.dao;

import org.example.model.Almazara;

import java.util.List;

public interface AlmazaraDAO {
    // Métodos CRUD
    void createAlmazara (Almazara almazara); // Crear
    Almazara findAlmazaraById (int idAlmazara); // Buscar Uno
    List<Almazara> findAllAlmazaras();// Buscar Todos
    void updateAlmazara (Almazara almazara); // Actualizar
    void deleteAlmazara (int idAlmazara); // Eliminar

    // Métodos específicos
    List<Almazara> getAlmazarasByCuadrilla(int idCuadrilla);
}
