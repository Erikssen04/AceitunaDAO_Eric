package org.example.DAOimp;

import org.example.controller.FactoriaConexion;
import org.example.dao.CuadrillaDAO;
import org.example.model.Cuadrilla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAOImp implements CuadrillaDAO {

    private Connection connection;

    public CuadrillaDAOImp() {
        this.connection = FactoriaConexion.getConnection();
    }

    // Metodo que añade la cuadrilla a la base de datos, pasando un objeto Almazara como parámetro
    @Override
    public void createCuadrilla(Cuadrilla cuadrilla) {
        String query = "INSERT INTO cuadrilla (nombre, supervisor_id) VALUES(?, ?);";
        try(PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, cuadrilla.getNombre());
            stmt.setInt(2, cuadrilla.getSupervisor_id());
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido añadir la nueva cuadrilla"+e.getMessage());

        }
    }

    // Metodo que encuentra la cuadrilla pasando su identificador como parámetro
    @Override
    public Cuadrilla findCuadrillaById(int idCuadrilla) {
        String query = "SELECT * FROM cuadrilla WHERE id = ?";
        Cuadrilla cuadrilla = null;

        try(PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int supervisorId = rs.getInt("supervisor_id");

                cuadrilla = new Cuadrilla(id, nombre, supervisorId);
            }
                return cuadrilla;

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido encontrar la Cuadrilla en la base de datos"+e.getMessage());

        }
    }

    // Metodo que lee todas las cuadrillas
    @Override
    public List<Cuadrilla> findAllCuadrillas() {
        List<Cuadrilla> cuadrillas = new ArrayList<>(); // Lista que guardará los objetos Cuadrilla
        String query = "SELECT * FROM cuadrilla";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int supervisorId = rs.getInt("supervisor_id");
                cuadrillas.add(new Cuadrilla(id, nombre, supervisorId));
            }
            return cuadrillas; // Regresa la lista con los objetos Cuadrilla guardados

        }catch (SQLException e) {
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido obtener todas las Almazaras"+e.getMessage());

        }
    }

    @Override
    public void updateCuadrilla(Cuadrilla cuadrilla) {
        String query = "UPDATE cuadrilla SET nombre = ?, supervisor_id = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, cuadrilla.getNombre());
            stmt.setInt(2, cuadrilla.getSupervisor_id());
            stmt.setInt(3, cuadrilla.getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido actualizar la Cuadrilla"+e.getMessage());

        }
    }

    @Override
    public void deleteCuadrilla(int idCuadrilla) {
        String query = "DELETE FROM cuadrilla WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idCuadrilla);
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido eliminar la Cuadrilla"+e.getMessage());

        }
    }

    public List<Cuadrilla> getCuadrillasByOlivar(int idOlivar){
        List<Cuadrilla> cuadrillas = new ArrayList<>();
        String query = "SELECT c.id, c.nombre, c.supervisor_id " +
                "FROM cuadrilla c " +
                "INNER JOIN cuadrilla_olivar co ON c.id = co.cuadrilla_id " +
                "WHERE co.olivar_id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idOlivar);
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int supervisor_id = rs.getInt("supervisor_id");
                cuadrillas.add(new Cuadrilla(id, nombre, supervisor_id));

            }
            return cuadrillas; // Regresa la lista con los objetos Cuadrilla guardados

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido obtener las cuadrillas"+e.getMessage());

        }
    }

    public List<Cuadrilla> getCuadrillasBySupervisor(int supervisorId){
        List<Cuadrilla> cuadrillas = new ArrayList<>();
        String query = "SELECT * FROM cuadrilla WHERE supervisor_id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, supervisorId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int supervisor_id = rs.getInt("supervisor_id");
                cuadrillas.add(new Cuadrilla(id, nombre , supervisor_id));

            }
            return cuadrillas;

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido obtener las cuadrillas"+e.getMessage());

        }
    }

    @Override
    public void asociarCuadrillaOlivar(int idCuadrilla, int idOlivar){
        String query = "INSERT INTO cuadrilla_olivar (cuadrilla_id, olivar_id) VALUES (?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idCuadrilla);
            stmt.setInt(2, idOlivar);
            stmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error: no se ha podido asociar la cuadrilla con el olivar"+e.getMessage());
        }
    }
}
