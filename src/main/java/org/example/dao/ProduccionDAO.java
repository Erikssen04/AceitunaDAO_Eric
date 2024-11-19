package org.example.dao;

import org.example.model.Produccion;

import java.util.List;

public interface ProduccionDAO {
    // Métodos CRUD
    void createProduccion (Produccion produccion); // Crear
    Produccion findProduccionById (int idProduccion); // Buscar Uno
    List<Produccion> findAllProducciones();// Buscar Todos
    void updateProduccion (Produccion produccion); // Actualizar
    void deleteProduccion (int idProduccion); // Eliminar

    // Métodos Específicos
    Produccion getProduccionByFechaCuadrillaAlmazara(String fecha, int idCuadrilla, int idAlmazara);
    List<Produccion> getProduccionByAlmazaraUntilFecha(String fecha, int idAlmazara);
    List<Produccion> getProduccionByCuadrillaUntilFecha(String fecha, int idCuadrilla);
    List<Produccion> getProduccionByOlivarUntilFecha(String fecha, int idOlivar);

}
