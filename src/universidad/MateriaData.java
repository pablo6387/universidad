/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class MateriaData {
    private Connection con;

    public MateriaData(Conexion conexion) {
        try{
            con = conexion.getConexion();
        }catch (SQLException ex){
            System.out.println("error de conexion");
        }
    }

    public void guardarMateria (Materia materia){
        String sql ="INSERT INTO MATERIA (nombre) VALUES (?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,materia.getNombre());
            
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener la materia");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar materia");
        }
    }
    
    
    public List<Materia> obtenerMaterias(){
        List<Materia> materias = new ArrayList<Materia>();
        try {
            String sql = "SELECT * FROM materia;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            Materia materia;
            while (rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error al listar materias");
        }
        return materias;
    }
    
    public Materia buscarMateria(int id){
            Materia materia = null;
        try {
            
            String sql = "SELECT * FROM materia WHERE idMateria =?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                
            }
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar materia");
        }
        return materia;
    }
    
    public void actualizarMateria (Materia materia){
        try {
            String sql = "UPDATE materia SET nombre = ? WHERE idMateria = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,materia.getNombre());
            ps.setInt(2, materia.getIdMateria());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar materia");
        }
    }
    
    
    public void eliminarMateria (int id){
        try {
            String sql = "DELETE FROM materia WHERE idMateria =?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar materia");
        }
    }
    
}
