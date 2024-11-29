package org.example.DAOimp;

import org.example.controller.FactoriaConexion;
import org.example.dao.AlmazaraDAO;
import org.example.model.Almazara;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlmazaraDAOImp implements AlmazaraDAO {

    private Connection connection;

    public AlmazaraDAOImp() {
        this.connection = FactoriaConexion.getConnection();
    }

    // Metodo que añade la almazara a la base de datos, pasando un objeto Almazara como parámetro
    @Override
    public void createAlmazara(Almazara almazara) {
        String query = "INSERT INTO almazara (nombre, ubicacion, capacidad) VALUES (?, ?, ?);";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, almazara.getNombre());
            stmt.setString(2, almazara.getUbicacion());
            stmt.setDouble(3, almazara.getCapacidad());
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido añadir la Almazara a la base de datos"+e.getMessage());
        }
    }

    // Metodo que encuentra la almazara pasando su identificador como parámetro
    @Override
    public Almazara findAlmazaraById(int idAlmazara) {
        String query = "SELECT * FROM almazara WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idAlmazara);
            ResultSet rs = stmt.executeQuery();


            if(rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                double capacidad = rs.getDouble("capacidad");

                return new Almazara(id, nombre, ubicacion, capacidad);
            }
            return null;
        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido encontrar la Almazara en la base de datos"+e.getMessage());
        }
    }

    // Metodo que lee todas las almazaras
    @Override
    public List<Almazara> findAllAlmazaras() {
        List<Almazara> almazaras = new ArrayList<>(); // Lista que guardará los objetos Almazara
        String query = "SELECT * FROM almazara";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                double capacidad = rs.getDouble("capacidad");

                almazaras.add(new Almazara(id, nombre, ubicacion, capacidad));
            }
            return almazaras; // Regresa la lista con los objetos Almazara guardados

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido obtener todas las Almazaras"+e.getMessage());

        }
    }

    // Metodo que actualiza la almazara, pasando un nuevo objeto Almazara como parámetro para actualizar datos
    @Override
    public void updateAlmazara(Almazara almazara) {
        String query = "UPDATE almazara SET nombre = ?, ubicacion = ?, capacidad = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, almazara.getNombre());
            stmt.setString(2, almazara.getUbicacion());
            stmt.setDouble(3, almazara.getCapacidad());
            stmt.setInt(4, almazara.getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido actualizar la Almazara"+e.getMessage());

        }
    }

    // Metodo para eliminar la almazara deseada, pasando su identificador como parámetro
    @Override
    public void deleteAlmazara(int idAlmazara) {
        String query = "DELETE FROM almazara WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idAlmazara);
            stmt.executeUpdate();

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: No se ha podido eliminar la Almazara seleccionada"+e.getMessage());

        }
    }

    public List<Almazara> getAlmazarasByCuadrilla(int idCuadrilla){
        List<Almazara> almazaras = new ArrayList<>();
        String query = "SELECT a.id, a.nombre, a.ubicacion, a.capacidad " +
                "FROM almazara a " +
                "INNER JOIN produccion p ON a.id = p.almazara_id " +
                "WHERE p.cuadrilla_id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto Almazara y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                double capacidad = rs.getDouble("capacidad");

                // Añadir almazaras encontradas a la lista
                almazaras.add(new Almazara(id, nombre, ubicacion, capacidad));
            }
            return almazaras; // Regresa la lista de Almazaras

        }catch (SQLException e){
            // Manejo de la Excepción SQL
            throw new RuntimeException("Error: no se ha podido obtener almazaras en las " +
                    "que trabaja una determinada cuadrilla"+e.getMessage());

        }
    }
}
