<%-- 
    Document   : loginAction
    Created on : 26 Jan, 2015, 2:23:36 PM
    Author     : abhi
--%>

<%@page import="database.DbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

String username=request.getParameter("username");
String password=request.getParameter("password");

String s=DbConnect.Login(username, password);
if(!s.equals("-1")) {
    session.setAttribute("username", username);
    response.sendRedirect("home.jsp");
}
else {
    out.print("<script>alert('Login Failed');window.location='index.jsp';</script>");
}

%>