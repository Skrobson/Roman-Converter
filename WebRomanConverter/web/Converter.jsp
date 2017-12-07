<%-- 
    Document   : Converter
    Created on : 2017-12-06, 23:55:46
    Author     : Skrobol Bartłomiej
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Roman Number Converter</title>
    </head>
    <body>
        
        <form action="ConverterServlet">
            <p>Type roman number</p>
            <input type="text" name="RomanNumber" value="" />
            <input type="submit" value="Convert" />   
        </form>
         <p>Number in decimal: </p>
            <%=(request.getAttribute("result"))%>
        
    </body>
</html>
