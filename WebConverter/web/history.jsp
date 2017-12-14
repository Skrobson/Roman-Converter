<%-- 
    Document   : history
    Created on : 2017-12-11, 17:56:29
    Author     : Skrobol Bartłomiej
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link  rel="icon" type="image/png" href="code_vision.png">
         <link rel="stylesheet" type="text/css" href="converterStyleSheet.css">
        <title>History</title>
    </head>
    <body>
        <h1>History</h1>
        <c:if test = "${ not empty error}">
            <p>Database error: <c:out value = "${error}" /></p>
        </c:if>	
        
        <table  border = "1"  class = "correctConversions" style="width:50%; float: left">
            <caption><h1>Correct conversions</h1></caption>
            <thead>
                <tr>
                <th class = "correctConversions" scope = "col">IP</th>
                <th class = "correctConversions" scope = "col">Roman number</th>
                <th class = "correctConversions" scope = "col">Result</th>
                <th class = "correctConversions" scope = "col">When</th>
                </tr>
                </thead>
            <c:forEach items="${correctConversions}" var="Conversion">
                <tr>
                    <td><c:out value="${Conversion.ip}" /></td>
                    <td><c:out value="${Conversion.romanNumber}" /></td>
                    <td><c:out value="${Conversion.result}" /></td>
                    <td><c:out value="${Conversion.timestamp}" /></td>
                </tr>
            </c:forEach>
        </table>
        
                
        <table  border = "1"  class = "incorrectConversions" style="width:50%; float: left" >
            <caption><h1>Incorrect conversions</h1></caption>
            <thead>
                <tr> 
                <th class = "incorrectConversions" scope = "col">IP</th>
                <th class = "incorrectConversions" scope = "col">Roman number</th>
                <th class = "incorrectConversions" scope = "col">When</th>
                </tr>
                </thead>
            <c:forEach items="${incorrectConversions}" var="Conversion">
                <tr>
                    <td><c:out value="${Conversion.ip}" /></td>
                    <td><c:out value="${Conversion.romanNumber}" /></td>
                    <td><c:out value="${Conversion.timestamp}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
