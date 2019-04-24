/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

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
    private final ArrayList<Usuario> listaUsuarios;

    public boolean addUsuario(String nom, String edad, String email, String password) {
        try {
            if (nom.equals("") || edad.equals("") || email.equals("") || password.equals("")) {
                return false;
            } else {
                int iEdad = Integer.parseInt(edad);
                Usuario nuevoUsu = new Usuario(nom, edad, email, password);
                this.listaUsuarios.add(nuevoUsu);
                return this.bdUsu.crear(nuevoUsu);
            }
        } catch (Exception ex) {
            System.out.println(" << ERROR: No se ha podido crear Usuario " + ex.getMessage());
            return false;
        }
    }

    public boolean deleteUsuario(Usuario user) {
        this.listaUsuarios.remove(user);
        this.bdUsu.delete(user);
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

    public boolean deleteUsuario(String email, String passwd) {
        for (Usuario user : listaUsuarios) {
            if (user.getEmail().equals(email)) {
                this.listaUsuarios.remove(user);
                this.bdUsu.delete(user);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Usuario> listar() {
        return this.listaUsuarios;
    }
}
