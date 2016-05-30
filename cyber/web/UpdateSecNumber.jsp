<%-- 
    Document   : UpdateSecNumber
    Created on : 31 Jan, 2015, 3:12:10 PM
    Author     : abhi
--%>

<%@page import="database.DbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

String num=request.getParameter("num");
DbConnect.UpdateSecondaryNumber(session.getAttribute("username").toString(), num);
%>

