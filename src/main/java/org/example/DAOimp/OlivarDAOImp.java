package org.example.DAOimp;

import org.example.controller.FactoriaConexion;
import org.example.dao.OlivarDAO;
import org.example.model.Olivar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OlivarDAOImp implements OlivarDAO {

    private Connection connection;

    public OlivarDAOImp() {
        this.connection = FactoriaConexion.getConnection();
    }

    @Override
    public void createOlivar(Olivar olivar) {
        String query = "INSERT INTO olivar (ubicacion, hectareas, produccionAnual) VALUES(?, ?, ?);";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, olivar.getUbicacion());
            stmt.setDouble(2, olivar.getHectareas());
            stmt.setDouble(3, olivar.getProduccionAnual());
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido añadir el nuevo Olivar"+e.getMessage());
        }
    }

    @Override
    public Olivar findOlivarById(int idOlivar) {
        String query = "SELECT * FROM olivar WHERE id = ?";
        Olivar olivar = null;

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idOlivar);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String ubicacion = rs.getString("ubicacion");
                double hectareas = rs.getDouble("hectareas");
                double produccionAnual = rs.getDouble("produccionAnual");

                olivar = new Olivar(id, ubicacion, hectareas, produccionAnual);
            }
            return olivar;

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido encontrar el Olivar en la base de datos"+e.getMessage());

        }

    }

    @Override
    public List<Olivar> findAllOlivares() {
        List <Olivar> olivares = new ArrayList<>();
        String query = "SELECT * FROM olivar";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int idOlivar = rs.getInt("id");
                String ubicacion = rs.getString("ubicacion");
                double hectareas = rs.getDouble("hectareas");
                double produccionAnual = rs.getDouble("produccionAnual");

                // Añadir objeto a la lista de olivares
                olivares.add(new Olivar(idOlivar, ubicacion, hectareas, produccionAnual));
            }

            return olivares;
        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se han podido obtener los olivares de la base de datos"+e.getMessage());

        }
    }

    @Override
    public void updateOlivar(Olivar olivar) {
        String query = "UPDATE olivar SET ubicacion = ?, hectareas = ?, produccionAnual = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, olivar.getUbicacion());
            stmt.setDouble(2, olivar.getHectareas());
            stmt.setDouble(3, olivar.getProduccionAnual());
            stmt.setInt(4, olivar.getId());

            stmt.executeUpdate();
        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido actualizar el olivar"+e.getMessage());

        }
    }

    @Override
    public void deleteOlivar(int idOlivar) {
        String query = "DELETE FROM olivar WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idOlivar);
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido eliminar el olivar"+e.getMessage());

        }
    }

    public List<Olivar> getOlivaresByCuadrilla(int idCuadrilla){
        List<Olivar> olivares = new ArrayList<>();
        String query = "SELECT o.id, o.ubicacion, o.hectareas, o.produccionAnual " +
                "FROM olivar " +
                "INNER JOIN cuadrilla_olivar co ON o.id = co.olivar_id " +
                "WHERE co.cuadrilla_id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                String ubicacion = rs.getString("ubicacion");
                double hectareas = rs.getDouble("hectareas");
                double produccionAnual = rs.getDouble("produccionAnual");

                // Añadir objeto a la lista de olivares
                olivares.add(new Olivar(id, ubicacion, hectareas, produccionAnual));
            }

            return olivares;
        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido obtener los olivares por cuadrilla"+e.getMessage());

        }
    }
}
