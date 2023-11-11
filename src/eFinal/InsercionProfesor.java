package eFinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsercionProfesor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresar información del profesor:");

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Profesión: ");
        String profesion = scanner.nextLine();

        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            
            conexion = ConexionBD.obtenerConexion();

          
            String sql = "INSERT INTO profesor (codigo, nombre, profesion) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(sql);

    
            statement.setString(1, codigo);
            statement.setString(2, nombre);
            statement.setString(3, profesion);

           
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("¡Inserción exitosa!");
            } else {
                System.out.println("No se pudo insertar el profesor.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
          
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            scanner.close();
        }
    }
}
