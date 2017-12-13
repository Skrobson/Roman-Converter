<%-- 
    Document   : index
    Created on : 2017-12-07, 12:14:11
    Author     : Skrobol Bartłomiej
--%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>		
 <!DOCTYPE html>		
 <html>		
     <head>		
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">		
         <title>Roman Number Converter</title>
         <link  rel="icon" type="image/png" href="WEB-INF/code vision.png">

    </head>		
    <body>		
        <h1>Roman number converter</h1>		
        <form action="ConverterServlet">		
             Type roman number:</br>		
             <input type="text" name="romanNumber" value="" />		
             <input type="submit" value="Convert" />   		
        </form>		
        
        <c:if test = "${ not empty result}">
            <p>Number in decimal: <c:out value = "${result}" /></p>
        </c:if>	

        <c:if test = "${not empty sqlError}">
            <p>Database error : 
            <c:out value = "${sqlError}"/><p>
        </c:if>
        <form action="DatabaseServlet">        
            <input type="submit" value="History" />        
        </form>
     </body>		
 </html>