/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Esto es una simulacion de un Modelo en la estructura MVC (modelo, vista,
 * controlador)
 *
 * @author Default
 */
public class ServicioUsuarios {

    // Implementando "Singleton"
    private static ServicioUsuarios instancia = null;

    private ServicioUsuarios() {
        //listaUsuarios = new ArrayList();
        this.bdUsu = new DerbyDBUsuario();
        this.listaUsuarios = bdUsu.listar();
        this.userLogged = null;
    }

    public static ServicioUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ServicioUsuarios();
        }
        return instancia;
    }

    // Codigo de la clase
    DerbyDBUsuario bdUsu;
    Usuario userLogged;
    private ArrayList<Usuario> listaUsuarios;

    public boolean addUsuario(String nom, String edad, String email, String password) {
        try {
            if (nom.equals("") || edad.equals("") || email.equals("") || password.equals("")) {
                return false;
            } else {
                Usuario nuevoUsu = new Usuario(null, nom, Integer.parseInt(edad), email, password);
                //this.listaUsuarios.add(nuevoUsu);
                boolean creado = this.bdUsu.crear(nuevoUsu);
                this.listaUsuarios = bdUsu.listar();
                return this.listaUsuarios != null && creado;
            }
        } catch (Exception ex) {
            System.out.println(" << ERROR: No se ha podido crear Usuario " + ex.getMessage());
            return false;
        }
    }
    
    public Usuario obtenerUno(String email) throws SQLException {
    return this.bdUsu.obtenerUno(email);
    }

    public boolean modificar(String id, String nom, String edad, String email, String password) {
        try {
            if (id.equals("") || nom.equals("") || edad.equals("") || email.equals("") || password.equals("")) {
                return false;
            } else {
                int iEdad = Integer.parseInt(edad);
                int iId = Integer.parseInt(id);
                // Creamos el usuario
                Usuario nuevoUsu = new Usuario(iId, nom, iEdad, email, password);
                //this.listaUsuarios.add(nuevoUsu);
                if (!this.bdUsu.isAlive(nuevoUsu)) {
                    boolean modificado = this.bdUsu.update(nuevoUsu);
                    this.listaUsuarios = bdUsu.listar();
                    return this.listaUsuarios != null && modificado;
                }

            }
        } catch (Exception ex) {
            System.out.println(" << ERROR: No se ha podido modificar Usuario " + ex.getMessage());
            return false;
        }
        return false;
    }

    public boolean delete(String id) {
        try {
            if (id.equals("")) {
                return false;
            } else {
                Integer iId = Integer.parseInt(id);
                boolean eliminado = this.bdUsu.delete(iId);
                this.listaUsuarios = bdUsu.listar();
                return this.listaUsuarios != null && eliminado;
            }
        } catch (Exception ex) {
            System.out.println(" << ERROR: No se ha podido eliminar Usuario " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteUsuario(int id) {
        this.listaUsuarios.remove(id);
        this.bdUsu.delete(id);
        return false;
    }

    public boolean validacionPasswd(String email, String passwd) {
        for (Usuario usu : listaUsuarios) {
            if (usu.getEmail().equals(email) && usu.getPassword().equals(passwd)) {
                return true;
            }
        }
        return false;
    }

    public int cantidadUsuarios() {
        return listaUsuarios.size();
    }

    public ArrayList<Usuario> listar() {
        return this.listaUsuarios;
    }
}
