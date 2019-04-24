/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author German
 */
public class HolaServlet extends HttpServlet {
    
    // Recibir método HTTP:
    @Override 
    protected void doGet(HttpServletRequest peticion, HttpServletResponse respuesta)
        throws ServletException, IOException {
        
        respuesta.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter salida = respuesta.getWriter()) {
            salida.println("<html>");
            salida.println("  <head>");
            salida.println("    <title>Hola desde el Servlet</title>");
            salida.println("  </head>");
            salida.println("  <body>");
            salida.println("    <h1>Hola desde el Servlet</h1>");
            salida.println("<h2> Ruta del servlet: " 
                    + peticion.getContextPath() + "<H2>");
            salida.println("<h2> M&eacute;todo de la peticion: " 
                    + peticion.getMethod()+ "<H2>");            
            salida.println("<ul>");
            for (int i = 0; i < 10; i++) {
                salida.println("<li>N&uacute;mero: " + i + "</li>");
            }
            salida.println("</ul>");
            salida.println("  </body>");
            salida.println("</html>");
        }
    }
}
