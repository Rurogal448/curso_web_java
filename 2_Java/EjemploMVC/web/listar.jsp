
<%@include file="head.jsp"%>

<% ArrayList<Usuario> listaUsu = ServicioUsuarios.getInstancia().listar();%>

<html>    
    <%=head()%>
    <body>
        <h1>Listado con JSP</h1>
        <table>
            <thread>
                <th>Nombre</th>
                <th>Email</th>
            </thread>
            <tbody>
                <%
                    for (Usuario usu : listaUsu) {
                %>
                <tr>
                    <td> <% out.println(usu.getNombre());%> </td>
                    <td> <%= usu.getEmail()%> </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
