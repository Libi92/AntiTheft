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

        <script src="http://maps.google.com/maps?file=api&v=2&key=ABQIAAAA7j_Q-rshuWkc8HyFI4V2HxQYPm-xtd00hTQOC0OXpAMO40FHAxT29dNBGfxqMPq5zwdeiDSHEPL89A" type="text/javascript"></script>
        <script>
            function initialize() {
                if (GBrowserIsCompatible()) {

                    var map = new GMap2(document.getElementById("map"));
                    map.setCenter(new GLatLng(10.0718, 76.5488), 9);
                    map.addControl(new GLargeMapControl());
                    map.addControl(new GScaleControl());

                    var point;
                    var marker;
            <%
//                        DbConnect d = new DbConnect();
                        ResultSet r = DbConnect.getLocation(session.getAttribute("username").toString());
                        while (r.next()) {
//                            System.out.println(r.getDouble(4));

            %>
                    var lat =<%=r.getDouble(3)%>;
                    var lng =<%=r.getDouble(4)%>;
                    point = new GLatLng(lat, lng);
//                    map.setCenter(point, 10);
                    marker = new GMarker(point);
//                    marker.openInfoWindowHtml("");
                    map.addOverlay(marker);
                    GEvent.addListener(marker, "mouseover", function () {

                    marker.openInfoWindow('My Device');

        });

            <% }%>
                }
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>

    </head>
    <body onload="initialize()">
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

            <div id="body" class="width" >
                <div id="map" style="height: 400px;width: 100%"></div>
<!--

                <section id="content" class="two-column with-right-sidebar">

                    <article>


                        <h2>device Location</h2>
                        <div id="map" class="article-info"><time datetime="2013-05-14"></time><a href="#" rel="author"></a></div>

                       
                    </article>


                </section>

                <aside class="sidebar big-sidebar right-sidebar">






                    <h4>Blocklist</h4>
                    <ul class="blocklist">
                        <li><a class="selected" href="index.html">Home Page</a></li>
                        <li><a href="examples.html"></a>
                            <ul>
                                <li><a href="three-column.html"></a></li>
                                <li><a href="one-column.html"></a></li>
                                <li><a href="text.html"></a></li>
                            </ul>
                        </li>

                    </ul>
                    </li>


                    </ul>

                </aside>
                <div class="clear"></div>-->
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