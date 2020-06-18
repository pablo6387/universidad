/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class testeo {
    public static void main (String[] arg){
        try {
            Alumno lucas = new Alumno("Pablo Lopez", new Date(87,3,6), true);
            Materia matematica = new Materia("Matematica");
            Cursada nueva = new Cursada(matematica,lucas, 9);
            Conexion c = new Conexion();
            AlumnoData ad = new AlumnoData(c);
            MateriaData md = new MateriaData(c);
            CursadaData cd = new CursadaData(c);
            ad.guardarAlumno(lucas);
            md.guardarMateria(matematica);
            cd.guardarCursada(nueva);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en la conexion");
        }
    }
}
