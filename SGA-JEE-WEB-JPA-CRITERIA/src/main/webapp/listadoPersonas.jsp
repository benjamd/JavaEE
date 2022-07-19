<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado De Personas</title>
    </head>
    <body>
        <h1>Listado De Personas</h1>
        <br/>
        <br/>
        <ul>
            <c:forEach items="${personas}" var="persona">
                <li>${persona.nombre} ${persona.apellido}</li>
             </c:forEach>
        </ul>
        <br/>
        <br/>

    </body>
</html>
