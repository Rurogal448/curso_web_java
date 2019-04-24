/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viewnext.controladores;

import com.modelo.ServicioUsuarios;
import com.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Formacion
 */
public class UsuariosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = request.getParameter("accion");
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String edad = request.getParameter("eda");
            String passwd = request.getParameter("passwd");

            switch (accion) {
                case "login":
                    if (ServicioUsuarios.getInstancia().validacionPasswd(email, passwd)) {
                        out.println("<h3> Login correcto </h3>");
                        HttpSession sesion = request.getSession();
                        Usuario usu = ServicioUsuarios.getInstancia().obtenerUno(email);
                        sesion.setAttribute("usuario",usu);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        out.println("<h3>Login incorrecto</h3>");
                    }
                    break;
                case "registro":
                    boolean realizado = ServicioUsuarios.getInstancia().addUsuario(nom, edad, email, passwd);
                    if (realizado) {
                        out.println("<h3> Usuario registrado correctamente</h3>");
                    } else {
                        out.println("<h3> El usuario no ha sido registrado. </h3>");
                    }
                    break;
            }

        } catch (Exception ex) {
            System.out.println(">>> ERROR Usuario.do" + ex.getMessage());
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
