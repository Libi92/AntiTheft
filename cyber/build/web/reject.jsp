<%@page import="database.DbConnect"%>
<%
    String id=request.getParameter("id");
    
    DbConnect.updateSim(id,"2");
    response.sendRedirect("home.jsp");
    %>