package org.example.dao;

import org.example.model.Trabajador;

import java.util.List;

public interface TrabajadorDAO {
    // Métodos CRUD
    void createTrabajador (Trabajador trabajador); // Crear
    Trabajador findTrabajadorById (int idTrabajador); // Buscar Uno
    List<Trabajador> findAllTrabajadores(); // Buscar Todos
    void updateTrabajador (Trabajador trabajador); // Actualizar
    void deleteTrabajador (int idTrabajador); // Eliminar

    // Métodos Específicos
    List<Trabajador> getTrabajadoresByCuadrilla (int idCuadrilla);
    void asociarTrabajadorCuadrilla(int idTrabajador, int idCuadrilla);
}
