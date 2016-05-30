<%@page import="database.DbConnect"%>
<%
    String id=request.getParameter("id");
    
    DbConnect.updateSim(id,"1");
    response.sendRedirect("home.jsp");
    %>