package org.example.dao;

import org.example.model.Cuadrilla;

import java.util.List;

public interface CuadrillaDAO {
    // Métodos CRUD
    void createCuadrilla (Cuadrilla cuadrilla); // Crear
    Cuadrilla findCuadrillaById (int idCuadrilla); // Buscar Uno
    List<Cuadrilla> findAllCuadrillas();// Buscar Todos
    void updateCuadrilla (Cuadrilla cuadrilla); // Actualizar
    void deleteCuadrilla (int Cuadrilla); // Eliminar

    // Métodos Específicos
    List<Cuadrilla> getCuadrillasByOlivar (int idOlivar);
    List<Cuadrilla> getCuadrillasBySupervisor (int supervisor_id);
    void asociarCuadrillaOlivar(int idCuadrilla, int idOlivar);
}