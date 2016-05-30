<%@page import="java.sql.ResultSet"%>
<%@page import="database.DbConnect"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>look out theft details</title>


<link rel="stylesheet" href="css/reset.css" type="text/css" />
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<!--<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">-->


<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/slider.js"></script>
<script type="text/javascript" src="js/superfish.js"></script>

<script type="text/javascript" src="js/custom.js"></script>

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

<!--
purplebiz, a free CSS web template by ZyPOP (zypopwebtemplates.com/)

Download: http://zypopwebtemplates.com/

License: Creative Commons Attribution
//-->
</head>
<body>
<div id="container">

    <header> 
	<div class="width">
    		<h1><a href="/">LOOK<strong>OUT</strong></a></h1>

		<nav>
	
    			<ul class="sf-menu dropdown">


                            <li class="selected"><a href="home.jsp"><i class="fa fa-home"></i> Home</a></li>

                            <li>

                                <a href="#"><i class="fa fa-code"></i> Add details</a>

                                <ul>
                                    <li><a href="javascript:AddEmail();">Update e-mail</a></li>
                                    <li><a href="javascript:UpdateNumber();">Update Primary number</a></li>

                                    <li><a href="javascript:UpdateSecNumber();">Update Secondary number</a></li>
                                </ul>

                            </li>


                            <li>

                                <a href="#"><i class="fa fa-database"></i> Details</a>

                                <ul>
                                    <li><a href="location.jsp">Location</a></li>
                                    
                                    <li><a href="one-column.jsp">Theft details</a></li>
                                    
                                    <li><a href="callsms.jsp">Calls & SMS</a></li>
                                </ul>

                            </li>


                            <li><a href="index.jsp"><i class="fa fa-phone"></i> Sign out</a></li>
                        </ul>

			
			<div class="clear"></div>
    		</nav>
       	</div>

	<div class="clear"></div>

       
    </header>


    <div id="body" class="width">

	<section id="content" class="one-column">

	    <article>
				
			
			<h2>Calls</h2>
			
                        <table>
                            
                            <tr>
                                <th>Phone No</th>
                                 <th>Type</th>
                                  <th>Duration</th>
                                  <th>Date - Time</th>
                            </tr>
                            <%
                                  ResultSet rs,rs1;
                                String username=session.getAttribute("username").toString();
                                System.out.println(username);
                                
                                DbConnect db=new DbConnect();
                                
                                rs1=db.select("select id from login where username='"+username+"'");
                                rs1.next();
                                
                                
                              rs=db.select("select * from callinfo where loginid='"+username+"' ORDER BY callid desc");
                              
                              while(rs.next())
                              {
                                %>
                            <tr>
                                <td><%=rs.getString("number")%></td>
                                 <td><%=rs.getString("type")%></td>
                                  <td><%=rs.getString("duration")%></td>
                                  <td><%=rs.getString("dateinfo")%></td>
                            </tr>
                            <%
                            
                              }
                            %>
                        </table>
		
		</article>
                            
                          
            <article>
				
			
			<h2>SMS</h2>
			
                        <table>
                            
                            <tr>
                                <th>Phone No</th>
                                 <th>Message</th>
                                  <th>Type</th>
                                  <th>Date - Time</th>
                            </tr>
                            <%
                                  ResultSet rs2,rs3;
//                                String username=session.getAttribute("username").toString();
                                
                                DbConnect db2=new DbConnect();
                                
                                rs3=db2.select("select id from login where username='"+username+"'");
                                rs3.next();
                                
                                
                              rs2=db2.select("select * from smsinfo where loginid='"+username + "' ORDER BY smsid desc");
                              
                              while(rs2.next())
                              {
                                %>
                            <tr>
                                <td><%=rs2.getString("phoneno")%></td>
                                 <td><%=rs2.getString("message")%></td>
                                  <td><%=rs2.getString("type")%></td>
                                  <td><%=rs2.getString("dateinfo")%></td>
                            </tr>
                            
                            <%
                            
                              }
                            %>
                        </table>
		
		</article>
	
		<article class="expanded">

            		<h2></h2>
			
		</article>
        </section>
        
        
    	<div class="clear"></div>
    </div>
    <footer>
        <div class="footer-content width">
            
 	                
            <ul class="endfooter">
            	
<div class="social-icons">

<a href="#"><i class="fa fa-facebook fa-2x"></i></a>

<a href="#"><i class="fa fa-twitter fa-2x"></i></a>

<a href="#"><i class="fa fa-youtube fa-2x"></i></a>

<a href="#"><i class="fa fa-instagram fa-2x"></i></a>

</div>

</li>
            </ul>
            
            <div class="clear"></div>
        </div>
        <div class="footer-bottom">
            <p><a href="http://zypopwebtemplates.com/"></a></p>
         </div>
    </footer>
</div>
</body>
</html>