/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

import java.util.ArrayList;

/**
 *
 * @author German
 */
public class ServicioUsuarios {

    // Implementando Singleton:
    private static ServicioUsuarios instancia = null;

    // Nadie puede hacer new salvo esta misma clase
    private ServicioUsuarios() {
        // listaUsuarios = new ArrayList<>();
        this.bdUsu = new DerbyDBUsuario();
        this.listaUsuarios = bdUsu.listar();
    }

    // Única manera de obtener un objeto en esta clase
    public static ServicioUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new ServicioUsuarios();
        }
        return instancia;
    }

    // Codigo de la clase:
    private DerbyDBUsuario bdUsu;
    private final ArrayList<Usuario> listaUsuarios;

    public boolean addUsuario(String nom, int edad, String email, String password) {
        Usuario nuevoUsu = new Usuario(nom, password, edad, email);
        this.listaUsuarios.add(nuevoUsu);
        this.bdUsu.crear(nuevoUsu);
        return true;
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
}
