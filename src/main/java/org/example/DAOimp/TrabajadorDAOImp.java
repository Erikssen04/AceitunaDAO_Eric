package org.example.DAOimp;

import org.example.controller.FactoriaConexion;
import org.example.dao.TrabajadorDAO;
import org.example.model.Trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAOImp implements TrabajadorDAO {

    private Connection connection;

    public TrabajadorDAOImp() {
        this.connection = FactoriaConexion.getConnection();
    }

    @Override
    public void createTrabajador(Trabajador trabajador) {
        String query = "INSERT INTO trabajador (nombre, edad, puesto, salario) VALUES (?, ?, ?, ?);";

        try(PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, trabajador.getNombre());
            stmt.setInt(2, trabajador.getEdad());
            stmt.setString(3, trabajador.getPuesto());
            stmt.setDouble(4, trabajador.getSalario());
            stmt.executeUpdate();

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido guardar al nuevo trabajador"+e.getMessage());

        }
    }

    @Override
    public Trabajador findTrabajadorById(int idTrabajador) {
        String query = "SELECT * FROM trabajador WHERE id = ?";
        Trabajador trabajador = null;

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idTrabajador);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String puesto = rs.getString("puesto");
                double salario = rs.getDouble("salario");

                trabajador = new Trabajador(id, nombre, edad, puesto, salario);

            }
            return trabajador;

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido encontrar al trabajador por su id"+e.getMessage());

        }
    }

    @Override
    public List<Trabajador> findAllTrabajadores() {
        String query = "SELECT * FROM trabajador";

        try(PreparedStatement stmt = connection.prepareStatement(query)){

            List<Trabajador> trabajadores = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String puesto = rs.getString("puesto");
                double salario = rs.getDouble("salario");

                trabajadores.add(new Trabajador(id, nombre, edad, puesto, salario));
            }
            return trabajadores;

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido obtener la lista con todos los trabajadores"+e.getMessage());

        }
    }

    @Override
    public void updateTrabajador(Trabajador trabajador) {
        String query = "UPDATE trabajador SET nombre = ?, edad = ?, puesto = ?, salario = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, trabajador.getNombre());
            stmt.setInt(2, trabajador.getEdad());
            stmt.setString(3, trabajador.getPuesto());
            stmt.setDouble(4, trabajador.getSalario());
            stmt.setInt(5, trabajador.getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido actualizar al trabajador por su id"+e.getMessage());

        }
    }

    @Override
    public void deleteTrabajador(int idTrabajador) {
        String query = "DELETE FROM trabajador WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idTrabajador);
            stmt.executeUpdate();

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido eliminar al trabajador por su id"+e.getMessage());

        }
    }

    @Override
    public List<Trabajador> getTrabajadoresByCuadrilla(int idCuadrilla) {
        List<Trabajador> trabajadores = new ArrayList<>();
        String query = "SELECT t.id, t.nombre, t.edad, t.puesto, t.salario " +
                "FROM trabajador t INNER JOIN cuadrilla_trabajador ct ON t.id = ct.trabajador_id " +
                "WHERE ct.cuadrilla_id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String puesto = rs.getString("puesto");
                double salario = rs.getDouble("salario");
                trabajadores.add(new Trabajador(id, nombre, edad, puesto, salario));
            }
            return trabajadores;

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido obtener aquellos trabajadores por su cuadrilla"+e.getMessage());

        }
    }

    @Override
    public void asociarTrabajadorCuadrilla(int idTrabajador, int idCuadrilla) {
        String query = "INSERT INTO cuadrilla_trabajador (cuadrilla_id, trabajador_id) VALUES (?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, idCuadrilla);
            stmt.setInt(2, idTrabajador);
            stmt.executeUpdate();

        }catch (SQLException e){
            //Manejo de Excepción SQL
            throw new RuntimeException("Error: no se ha podido asociar al trabajador con la cuadrilla deseada"+e.getMessage());
        }
    }
}
