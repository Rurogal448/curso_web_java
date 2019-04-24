/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public Usuario obtenerUno(String email) {
        try (Connection con = DriverManager.getConnection(Constantes.CONEX_DB, Constantes.USUARIO_DB, Constantes.PASSWORD_DB)) {
            String consultaSQL = "SELECT id, nombre, edad, email, password" + " FROM Usuario WHERE email='" + email + "'";
            Statement sentencia = con.createStatement();
            ResultSet res = sentencia.executeQuery(consultaSQL);
            Usuario usu = null;
            if (res.next()) {
                Integer id = res.getInt("id");
                String nom = res.getString("nombre");
                int edad = res.getInt("edad");
                String password = res.getString("password");
                usu = new Usuario(id, nom, edad, email, password);
            }
            return usu;
        } catch (SQLException ex) {
            System.err.println(" >>>>>> " + ex.getMessage());
            return null;
        }
    }

    public ArrayList<Usuario> listar() {
        try (Connection con = DriverManager.getConnection(Constantes.CONEX_DB, Constantes.USUARIO_DB, Constantes.PASSWORD_DB)) {
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
                Usuario usu = new Usuario(id, nombre, edad, email, password);
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
        try (Connection con = DriverManager.getConnection(Constantes.CONEX_DB, Constantes.USUARIO_DB, Constantes.PASSWORD_DB)) {
            Statement sentencia = con.createStatement();
            String querySQL
                    = "INSERT INTO USUARIO (NOMBRE, EDAD, EMAIL, PASSWORD)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(querySQL);
            st.setString(1, user.getNombre());
            st.setInt(2, user.getEdad());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(" >>>>>> " + ex.getMessage());
        }

        return false;
    }

    public boolean delete(int id) {
        try (Connection con = DriverManager.getConnection(Constantes.CONEX_DB, Constantes.USUARIO_DB, Constantes.PASSWORD_DB)) {
            String querySQL
                    = "DELETE FROM USUARIO WHERE id=?";
            PreparedStatement st = con.prepareStatement(querySQL);
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(" >>>>>> " + ex.getMessage());
        }
        return false;
    }

    public boolean update(Usuario user) {
        try (Connection con = DriverManager.getConnection(Constantes.CONEX_DB, Constantes.USUARIO_DB, Constantes.PASSWORD_DB)) {
            Statement sentencia = con.createStatement();
            String querySQL
                    = "UPDATE USUARIO SET (NOMBRE=?, EDAD=?, EMAIL=?, PASSWORD=?)"
                    + " WHERE id=? ";
            PreparedStatement st = con.prepareStatement(querySQL);
            st.setString(1, user.getNombre());
            st.setInt(2, user.getEdad());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setInt(5, user.getId());
            st.executeUpdate();
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
