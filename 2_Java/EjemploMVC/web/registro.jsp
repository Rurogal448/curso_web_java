<%@include file="head.jsp"%>

<html>    
    <%=head()%>
    <body>
        <%@include file="header.jsp"%>
        <h2>Registro</h2>
        <form name="form1" method="post" action="./usuarios.do">
            <table border="1">
                <tr>
                    <td>Nombre:</td>
                    <td>
                        <input type="text" name="nom" id="nom" size="25" value="Rocio Prueba" required="required"/>
                    </td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td>
                        <input type="password" name="passwd" id="pass" size="25" value="1234" required="required"/>
                    </td>
                </tr>
                <tr>
                    <td>Edad:</td>
                    <td>
                        <input type="number" name="eda" id="eda" size="25" value="27"/>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <input type="email" name="email" id="email" size="25" value="email@hotmail.com" required="required"/>
                    </td>
                </tr>
            </table>
                <input name="accion" id="accion" type="hidden" value="registro"/>
                <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
