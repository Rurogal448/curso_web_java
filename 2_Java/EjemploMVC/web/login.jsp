<%@include file="head.jsp"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%=head()%>
    <body>
        <%@include file="header.jsp"%>
        <form name="form1" method="post" action="./usuarios.do">
            <table border="1">
                <tr>
                    <td>Email:</td>
                    <td>
                        <input type="email" name="email" id="email" size="25" value="email@hotmail.com" required="required"/>
                    </td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td>
                        <input type="password" name="passwd" id="pass" size="25" value="1234" required="required"/>
                    </td>
                </tr>
            </table>
            <input name="accion" id="accion" type="hidden" value="login"/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
