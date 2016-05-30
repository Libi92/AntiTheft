<%-- 
    Document   : registerAction
    Created on : 26 Jan, 2015, 2:23:02 PM
    Author     : abhi
--%>

<%@page import="database.DbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String name = request.getParameter("name");
    String pno = request.getParameter("pno");
    String sno = request.getParameter("sno");
    String email = request.getParameter("email");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
//String rep=request.getParameter("username");

    boolean r = DbConnect.Register(name, pno, sno, email, username, password);


    if (r) {
        response.sendRedirect("index.jsp");
        out.print("<script>alert('Registration succesfull');window.location='index.jsp';</script>");
    } else {
        out.print("<script>alert('Registration Failed');window.location='index.jsp';</script>");
    }
%>
