<%-- 
    Document   : index
    Created on : 2017-12-06, 22:22:43
    Author     : Skrobol Bartłomiej
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <form action="ConverterServlet">
            Type roman number:</br>
            <input type="text" name="romanNumber" value="" />
            <input type="submit" value="Convert" />   
        </form>
         <p>Number in decimal: </p>
         <%=(request.getAttribute("result"))%>

    </body>
</html>
