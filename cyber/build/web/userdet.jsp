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


                            <li class="selected"><a href="adminhome.jsp"><i class="fa fa-home"></i> Home</a></li>

                            

                            <li>

                                <a href="userdet"><i class="fa fa-database"></i> UserDetails</a>

                                

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
				
			
			<h2>User Approval</h2>
			
                        <table>
                            
                            <tr>
                                <th>Name</th>
                                 <th>Primary No</th>
                                  <th>Secondary No</th>
                                  <th>Email</th>
                                  <th>Approve</th>
                                  <th>Reject</th>
                            </tr>
                            <%
                                  ResultSet rs,rs1;
                               
                                DbConnect db=new DbConnect();
                                
                                rs1=db.select("select * from reg");
                              
                              while(rs1.next())
                              {
                                %>
                            <tr>
                                <td><%=rs1.getString("name")%></td>
                                 <td><%=rs1.getString("pno")%></td>
                                  <td><%=rs1.getString("sno")%></td>
                                  <td><%=rs1.getString("email")%></td>
                                  
                                  
                                  <td><a href="approve.jsp?id=<%=rs1.getString("id")%>">Approve</a></td>
                        <td><a href="delete.jsp?id=<%=rs1.getString("id")%>">Reject</a></td>
                       
                            
                            </tr>
                            <%
                            
                              }
                            %>
                        </table>
		
		</article>
                            
                          
            <article>
				
			
			
		
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