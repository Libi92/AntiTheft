<%-- 
    Document   : updateNumber
    Created on : 31 Jan, 2015, 2:58:18 PM
    Author     : abhi
--%>

<%@page import="database.DbConnect"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

String num=request.getParameter("num");
DbConnect.UpdatePrimaryNumber(session.getAttribute("username").toString(), num);
%>
