/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Default
 */
public class DerbyDBUsuario {

    public DerbyDBUsuario() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (Exception e) {
            Logger.getLogger(DerbyDBUsuario.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ArrayList<Usuario> listar() {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/UsuariosVNext", "administrador", "1234")) {
            ArrayList<Usuario> listaUsu = new ArrayList<>();
            String consultaSQL = "SELECT id, nombre, edad, email, password FROM Usuario"; //consulta que hacemos a la bd
            Statement sentencia = con.createStatement(); // creamos primero la sentencia
            ResultSet res = sentencia.executeQuery(consultaSQL); // ahora hacemos la consulta
            while (res.next()) {
                int id = res.getInt("id");
                String nombre = res.getString("nombre");
                String email = res.getString("email");
                String password = res.getString("password");
                int edad = res.getInt("edad");
                Usuario usu = new Usuario(nombre, edad, email, password);
                listaUsu.add(usu);
            }

            // Si todo funciona:
            return listaUsu;

        } catch (SQLException ex) {
            System.err.println(" >>>>>> " + ex.getMessage());
            return null;
        }
    }

    public boolean crear(Usuario user) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/UsuariosVNext", "administrador", "1234")) {
            // Primero comprobamos si existe el usuario
            if (isAlive(user)) {
                if (update(user)) { // Si hemos podido actualizar devolvemos true, sino false
                    return true;
                }
            } else {
                // Si no existe lo creamos
                String sqlID = "SELECT COUNT(id) as ultId FROM Usuario";
                Statement sentencia = con.createStatement(); // creamos primero la sentencia
                ResultSet res = sentencia.executeQuery(sqlID); // ahora hacemos la consulta
                if (res.next()) {
                    int ultId = res.getInt("ultId");
                    ultId++;
                    String sqlInsert = "INSERT INTO usuario (id, nombre, email, password, edad) VALUES("
                            + ultId + ", '"
                            + user.getNombre() + "' , '"
                            + user.getEmail() + "', '"
                            + user.getPassword() + "',"
                            + user.getEdad() + " )";
                    System.err.println(" >>>>>>>  " + sqlInsert);
                    sentencia = con.createStatement();
                    sentencia.executeUpdate(sqlInsert);
                    return true;
                }
            }

            // Si todo funciona:
            return false;

        } catch (SQLException ex) {
            System.err.println(" >>>>>> " + ex.getMessage());
        }
        return false;
    }

     public boolean delete(Usuario user) {

        try (Connection con = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/UsuariosVNext", "administrador", "1234")) {
            String sqlID = "DELETE FROM Usuario WHERE email='" + user.getEmail() + "'";
            Statement sentencia = con.createStatement();
            sentencia.executeUpdate(sqlID);
            return true;
        } catch (SQLException ex) {
            System.err.println(" >>>>  " + ex.getMessage());
            return false;
        }
     }
     
    public boolean update(Usuario user) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/UsuariosVNext", "administrador", "1234")) {
            // Actualizamos los datos
            String sqlID = "UPDATE Usuario SET nombre = '" + user.getNombre()
                    + "', email = '" + user.getEmail()
                    + "', edad = " + user.getEdad()
                    + ", password = '" + user.getPassword()
                    + "' WHERE email='" + user.getEmail() + "' ";
            Statement sentencia = con.createStatement(); // creamos primero la sentencia
            sentencia.executeUpdate(sqlID); // ahora hacemos la consulta
            return true;
        } catch (SQLException ex) {
            System.err.println(" >>>>>> " + ex.getMessage());
        }

        return false;
    }

    public boolean isAlive(Usuario user) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/UsuariosVNext", "administrador", "1234")) {
            // Buscamos si existe
            String consultaSQL = "SELECT id, nombre, edad, email, password FROM Usuario WHERE email='" + user.getEmail() + "'";
            Statement sentencia = con.createStatement(); // creamos primero la sentencia
            ResultSet res = sentencia.executeQuery(consultaSQL); // ahora hacemos la consulta
            int id = -1;
            String nombre = "";
            String email = "";
            String password = "";
            while (res.next()) {
                id = res.getInt("id");
                nombre = res.getString("nombre");
                email = res.getString("email");
                password = res.getString("password");
            }

            if (id > 0 && email.equals(user.getEmail())) {
                return true;
            }
        } catch (SQLException ex) {
            System.err.println(" >>>>>> " + ex.getMessage());
        }
        return false;
    }
    

    }

