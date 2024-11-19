package org.example.DAOimp;

import org.example.controller.FactoriaConexion;
import org.example.dao.ProduccionDAO;
import org.example.model.Produccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduccionDAOImp implements ProduccionDAO {

    private Connection connection;

    public ProduccionDAOImp() {
        this.connection = FactoriaConexion.getConnection();
    }

    @Override
    public void createProduccion(Produccion produccion) {
        String query = "INSERT INTO produccion (cuadrilla_id, olivar_id, almazara_id, fecha, cantidadRecolectada) VALUES (?, ?, ?, ?, ?);";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, produccion.getCuadrillaId());
            stmt.setInt(2, produccion.getOlivarId());
            stmt.setInt(3, produccion.getAlmazaraId());
            stmt.setDate(4, Date.valueOf(produccion.getFecha()));
            stmt.setDouble(5, produccion.getCantidadRecolectada());
            stmt.executeUpdate();

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido añadir la nueva producción"+e.getMessage());

        }
    }

    @Override
    public Produccion findProduccionById(int idProduccion) {
        String query = "SELECT * FROM produccion WHERE id = ?";
        Produccion produccion = null;

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1 ,idProduccion);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                int cuadrillaId = rs.getInt("cuadrilla_id");
                int olivarId = rs.getInt("olivar_id");
                int almazarId = rs.getInt("almazara_id");
                String fecha = String.valueOf(rs.getDate("fecha"));
                double cantidadRecolectada = rs.getDouble("cantidadRecolectada");

                produccion = new Produccion(id, cuadrillaId, olivarId, almazarId, fecha, cantidadRecolectada);

            }
            return produccion;

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido encontrar la producción por su id"+e.getMessage());
        }
    }

    @Override
    public List<Produccion> findAllProducciones() {
        List<Produccion> producciones = new ArrayList<>();
        String query = "SELECT * FROM produccion";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                int cuadrillaId = rs.getInt("cuadrilla_id");
                int olivarId = rs.getInt("olivar_id");
                int almazarId = rs.getInt("almazara_id");
                String fecha = String.valueOf(rs.getDate("fecha"));
                double cantidadRecolectada = rs.getDouble("cantidadRecolectada");

                // Añade cada objeto a la lista de producciones
                producciones.add(new Produccion(id, cuadrillaId, olivarId, almazarId, fecha, cantidadRecolectada));

            }
            return producciones;

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido encontrar la lista que contiene toda la producción"+e.getMessage());

        }
    }

    @Override
    public void updateProduccion(Produccion produccion) {

        String query = "UPDATE produccion SET cuadrilla_id = ?, olivar_id = ?, almazara_id = ?, fecha = ?, cantidadRecolectada = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, produccion.getCuadrillaId());
            stmt.setInt(2, produccion.getOlivarId());
            stmt.setInt(3, produccion.getAlmazaraId());
            stmt.setDate(4, Date.valueOf(produccion.getFecha()));
            stmt.setDouble(5, produccion.getCantidadRecolectada());
            stmt.setInt(6, produccion.getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido actualizar la producción por su id"+e.getMessage());

        }
    }

    @Override
    public void deleteProduccion(int idProduccion) {
        String query = "DELETE FROM produccion WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idProduccion);
            stmt.executeUpdate();

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido eliminar la producción por su id"+e.getMessage());

        }
    }

    public Produccion getProduccionByFechaCuadrillaAlmazara(String fecha, int idCuadrilla, int idAlmazara){
        String query = "SELECT * FROM produccion WHERE fecha = ? AND cuadrilla_id = ? AND almazara_id = ?";
        Produccion produccion = null;

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setInt(2, idCuadrilla);
            stmt.setInt(3, idAlmazara);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                int cuadrilla_id = rs.getInt("cuadrilla_id");
                int olivar_id = rs.getInt("olivar_id");
                int almazara_id = rs.getInt("almazara_id");
                String fechaProduccion = String.valueOf(rs.getDate("fecha"));
                double cantidadRecolectada = rs.getDouble("cantidadRecolectada");

                produccion = new Produccion(id, cuadrilla_id, olivar_id, almazara_id, fechaProduccion, cantidadRecolectada);

            }
            return produccion;

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido encontrar la producción según la fecha"+e.getMessage());

        }
    }

    public List<Produccion> getProduccionByAlmazaraUntilFecha(String fecha, int idAlmazara){
        String query = "SELECT * FROM produccion WHERE fecha <= ? AND almazara_id = ?";
        List<Produccion> producciones = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setInt(2, idAlmazara);
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                int cuadrilla_id = rs.getInt("cuadrilla_id");
                int olivar_id = rs.getInt("olivar_id");
                int almazara_id = rs.getInt("almazara_id");
                String fechaProduccion = String.valueOf(rs.getDate("fecha"));
                double cantidadRecolectada = rs.getDouble("cantidadRecolectada");

                // Añade cada objeto a la lista de producciones
                producciones.add(new Produccion(id, cuadrilla_id, olivar_id, almazara_id, fechaProduccion, cantidadRecolectada));
            }

            return producciones;
        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido encontrar la producción según la fecha de la almazara"+e.getMessage());
        }
    }

    public List<Produccion> getProduccionByCuadrillaUntilFecha(String fecha, int idCuadrilla){
        String query = "SELECT * FROM produccion WHERE fecha <= ? AND cuadrilla_id = ?";
        List<Produccion> producciones = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setInt(2, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                int cuadrilla_id = rs.getInt("cuadrilla_id");
                int olivar_id = rs.getInt("olivar_id");
                int almazara_id = rs.getInt("almazara_id");
                String fechaProduccion = String.valueOf(rs.getDate("fecha"));
                double cantidadRecolectada = rs.getDouble("cantidadRecolectada");

                // Añade cada objeto a la lista de producciones
                producciones.add(new Produccion(id, cuadrilla_id, olivar_id, almazara_id, fechaProduccion, cantidadRecolectada));
            }

            return producciones;
        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido encontrar la producción segun la fecha de la cuadrilla"+e.getMessage());
        }
    }

    public List<Produccion> getProduccionByOlivarUntilFecha(String fecha, int idOlivar){
        String query = "SELECT * FROM produccion WHERE fecha <= ? AND olivar_id = ?";
        List<Produccion> producciones = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setInt(2, idOlivar);
            ResultSet rs = stmt.executeQuery();

            // Bucle que itera por cada fila, crea objeto y lo añade a la lista
            while(rs.next()){
                int id = rs.getInt("id");
                int cuadrilla_id = rs.getInt("cuadrilla_id");
                int olivar_id = rs.getInt("olivar_id");
                int almazara_id = rs.getInt("almazara_id");
                String fechaProduccion = String.valueOf(rs.getDate("fecha"));
                double cantidadRecolectada = rs.getDouble("cantidadRecolectada");

                // Añade cada objeto a la lista de producciones
                producciones.add(new Produccion(id, cuadrilla_id, olivar_id, almazara_id, fechaProduccion, cantidadRecolectada));
            }

            return producciones;
        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido encontrar la producción segun la fecha del olivar"+e.getMessage());
        }
    }


}
