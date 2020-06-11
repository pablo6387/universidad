/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class AlumnoData {
    private Connection con;

    public AlumnoData(Conexion conexion) {
        try{
            con = conexion.getConexion();
        }catch (SQLException ex){
            System.out.println("error de conexion");
        }
    }
    
    public void guardarAlumno (Alumno alumno){
        String sql ="INSERT INTO ALUMNO (nombre, fechNac, activo) VALUES (?,?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,alumno.getNombre());
            ps.setDate(2,alumno.getFechaNac());
            ps.setBoolean(3, alumno.getActivo());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener el alumno");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar alumno");
        }
    }
    
    public List<Alumno> obtenerAlumnos(){
        List<Alumno> alumnos = new ArrayList<Alumno>();
        try {
            String sql = "SELECT * FROM alumno;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            Alumno alumno;
            while (rs.next()){
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechNac"));
                alumno.setActivo(rs.getBoolean("activo"));
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error al listar alumnos");
        }
        return alumnos;
    }
    
    public Alumno buscarAlumno(int id){
            Alumno alumno = null;
        try {
            
            String sql = "SELECT * FROM alumno WHERE idAlumno =?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechNac"));
                alumno.setActivo(rs.getBoolean("activo"));
            }
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar alumno");
        }
        return alumno;
    }
    
    public void actualizarAlumno (Alumno alumno){
        try {
            String sql = "UPDATE alumno SET nombre = ?, fechNac = ?, activo = ? WHERE idAlumno = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,alumno.getNombre());
            ps.setDate(2,alumno.getFechaNac());
            ps.setBoolean(3, alumno.getActivo());
            ps.setInt(4, alumno.getIdAlumno());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar alumno");
        }
    }
    
    public void eliminarAlumno (int id){
        try {
            String sql = "DELETE FROM alumno WHERE idAlumno =?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar alumno");
        }
    }
}
