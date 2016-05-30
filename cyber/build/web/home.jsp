<%@page import="java.sql.ResultSet"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="database.DbConnect"%>
<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>look out home</title>


        <link rel="stylesheet" href="css/reset.css" type="text/css" />
        <link rel="stylesheet" href="css/styles.css" type="text/css" />



        <!--[if lt IE 9]>
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/slider.js"></script>
        <script type="text/javascript" src="js/superfish.js"></script>

        <script type="text/javascript" src="js/custom.js"></script>
        <!--
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" /> -->

        <!--
        
        
        //-->

        <script>
            function AddEmail()
            {
                var email = prompt("enter your email:", "");

            }
            function UpdateNumber()
            {
            <%
                String newNum = "";
                System.out.println(session.getAttribute("username"));
                String pno = DbConnect.getPrimaryNumber(session.getAttribute("username").toString());
                System.out.println(pno);
                String sno = DbConnect.getSecondaryNumber(session.getAttribute("username").toString());
            %>
                var pno =<%=pno%>;
                var p = prompt("Update Number", pno);
//                    alert(p);
                update(p);
            }

        </script>  

        <script>
            function UpdateSecNumber()
            {
            <%
                String newNu = "";
                System.out.println(session.getAttribute("username"));
                String peno = DbConnect.getPrimaryNumber(session.getAttribute("username").toString());
                System.out.println(pno);
                String seno = DbConnect.getSecondaryNumber(session.getAttribute("username").toString());
            %>
                var seno =<%=seno%>;
                var s = prompt("Update Number", seno);
//                    alert(p);
                updatesec(s);


            }
            function CuNu()
            {
                var cn = prompt("Currently used number is:", "");
            }
        </script>
        <script>
            function update(str)
            {
                if (window.XMLHttpRequest)
                {
                    xmlhttp = new XMLHttpRequest();
                }
                else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        //                        document.getElementById("details").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET", "updateNumber.jsp?num=" + str, true);
                xmlhttp.send();
            }
        </script>


        <script>
            function updatesec(str)
            {
                if (window.XMLHttpRequest)
                {
                    xmlhttp = new XMLHttpRequest();
                }
                else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function() {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        //                        document.getElementById("details").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("GET", "UpdateSecNumber.jsp?num=" + str, true);
                xmlhttp.send();
            }
        </script>





    </head>
    <body>
        <div id="container" class="width">

            <header> 
                <div class="width">

                    <h1><a href="/">LOOK OUT</a></h1>

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


            <div id="intro">

                <div class="width">

                    <div class="intro-content">

                        <h2>ANTI THEFT APPLICATION </h2>
                        <p></p>

                        <p><a href="AntiTheftApp.apk" class="button button-slider"><i class="fa fa-gbp"></i> Download</a>



                    </div>

                </div>


            </div>

            <div id="body" class="width">



                <section id="content" class="two-column with-right-sidebar">

                    <article>


                        <h2>My Devices</h2>
                        <div class="article-info"><time datetime="2013-05-14"></time><a href="#" rel="author"></a></div>

                        <table >
                            <tr>
                                <td>Device ID</td>
                                
                            </tr>
                            <% 
                                ResultSet rs = DbConnect.getSimDetails(session.getAttribute("username").toString());
                                while (rs.next()) {
                            %>
                            <tr><td><%=rs.getString("deviceId")%></td></tr>

                            <% if (rs.getString("status").equals("0")) {
                            %>

                            <tr><td><a href="approve.jsp?id=<%=rs.getString("id")%>">Accept</a> </td></tr>
                            <%
                            } else {
                            %>
                            <tr><td><a href="reject.jsp?id=<%=rs.getString("id")%>">Reject</a> </td></tr>
                            <%
                                    }
                                }

                            %>
                        </table>

                    </article>


                </section>

                <aside class="sidebar big-sidebar right-sidebar">






                    
                    
                </aside>
                <div class="clear"></div>
            </div>
            <footer>
                <div class="footer-content width">
                    

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
                    <input type="text" name="phNum" id="phNum"/>
                    <p>copy right&copy; YourSite 2014.
                    </p>
                </div>
            </footer>
        </div>
    </body>
</html>