/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modelo;

/**
 *  Clase tipo POJO para Entidad Usuario
 * @author German
 */
public class Usuario {    

    private String nombre;
    private String password;
    private int edad;
    private String email;

    public Usuario(String nombre, String password, int edad, String email) {
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;
        this.email = email;
    }

    /**
     * Get the value of edad
     *
     * @return the value of edad
     */
   
    public int getEdad() {
        return edad;
    }
    
    /**
     * Set the value of edad
     *
     * @param edad new value of edad
     */
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Get the value of email
     *
     * @return the value of email
     */
    
    public String getEmail() {
        return email;
    }
    
    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Get the value of password
     *
     * @return the value of password
     */
    
    public String getPassword() {
        return password;
    }
    
    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
