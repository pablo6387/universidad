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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class CursadaData {
    private Connection con;
    Conexion caux;

    public CursadaData(Conexion conexion) {
        try{
            con = conexion.getConexion();
            caux = conexion;
        }catch (SQLException ex){
            System.out.println("error de conexion");
        }
    }
    
     public void guardarCursada (Cursada cursada){
        String sql ="INSERT INTO cursada (idMateria, idAlumno, nota) VALUES (?,?,?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cursada.getMateria().getIdMateria());
            ps.setInt(2,cursada.getAlumno().getIdAlumno());
            ps.setDouble(3, cursada.getNota());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                cursada.setIdCursada(rs.getInt(1));
            }else{
                System.out.println("No se pudo obtener la cursada");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar cursada");
        }
    }
     
     public List<Cursada> obtenerCursadas(){
        List<Cursada> cursadas = new ArrayList<Cursada>();
        try {
            String sql = "SELECT * FROM cursada;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            Cursada cursada;
            
            while (rs.next()){
                cursada = new Cursada();
                cursada.setIdCursada(rs.getInt("idCursada"));
                cursada.setMateria(buscarMateria(rs.getInt("idMateria")));
                cursada.setAlumno(buscarAlumno(rs.getInt("idAlumno")));
                cursada.setNota(rs.getDouble("nota"));
                cursadas.add(cursada);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error al listar materias");
        }
        return cursadas;
    }
     
     public List<Cursada> obtenerCursadasXAlumno(int id){
        List<Cursada> cursadas = new ArrayList<Cursada>();
        try {
            String sql = "SELECT * FROM cursada WHERE idAlumno = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            Cursada cursada;
            
            while (rs.next()){
                cursada = new Cursada();
                cursada.setIdCursada(rs.getInt("idCursada"));
                cursada.setMateria(buscarMateria(rs.getInt("idMateria")));
                cursada.setAlumno(buscarAlumno(rs.getInt("idAlumno")));
                cursada.setNota(rs.getDouble("nota"));
                cursadas.add(cursada);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("error al listar materias");
        }
        return cursadas;
    }
    
     public Cursada buscarCursada(int id){
            Cursada cursada = null;
        try {
            
            String sql = "SELECT * FROM cursada WHERE idCursada =?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                cursada = new Cursada();
                cursada.setIdCursada(rs.getInt("idCursada"));
                cursada.setMateria(buscarMateria(rs.getInt("idMateria")));
                cursada.setAlumno(buscarAlumno(rs.getInt("idAlumno")));
                
                cursada.setNota(rs.getDouble("nota"));
            }
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar cursada");
        }
        return cursada;
    }
     
     
     public void actualizarCursada (Cursada cursada){
        try {
            String sql = "UPDATE cursada SET idMateria = ?, idAlumno = ?, nota = ? WHERE idCursada = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cursada.getMateria().getIdMateria());
            ps.setInt(2, cursada.getAlumno().getIdAlumno());
            ps.setDouble(3, cursada.getNota());
            ps.setInt(4, cursada.idCursada);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar cursada");
        }
    }
     
     public void eliminarCursada (int id){
        try {
            String sql = "DELETE FROM cursada WHERE idCursada =?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar cursada");
        }
    }
     
     public Alumno buscarAlumno(int id){
         AlumnoData ad = new AlumnoData(caux);
         return ad.buscarAlumno(id);
     }
     
     public Materia buscarMateria(int id){
         MateriaData md = new MateriaData(caux);
         return md.buscarMateria(id);
     }
}
