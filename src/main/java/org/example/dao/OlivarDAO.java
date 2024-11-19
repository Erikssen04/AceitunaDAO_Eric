package org.example.dao;

import org.example.model.Olivar;

import java.util.List;

public interface OlivarDAO {
    // Métodos CRUD
    void createOlivar (Olivar olivar); // Crear
    Olivar findOlivarById (int idOlivar); // Buscar Uno
    List<Olivar> findAllOlivares();// Buscar Todos
    void updateOlivar (Olivar olivar); // Actualizar
    void deleteOlivar (int idOlivar); // Eliminar

    // Métodos especificos
    List<Olivar> getOlivaresByCuadrilla(int idCuadrilla);
}