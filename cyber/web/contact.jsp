<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>LOOK OUT</title>


        <link rel="stylesheet" href="css/reset.css" type="text/css" />
        <link rel="stylesheet" href="css/styles.css" type="text/css" />
        


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

        <script>
            function validatePass()
            {
                var pass=document.getElementById("password").value;
                var cpass=document.getElementById("cpassword").value;
        
                if(pass==cpass)
                {
                    return true;
                }
                else
                {
                    alert("Passwords are mismatch");
                    return false;
                }
            }
        </script>
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
                    </nav>
                </div>

                <div class="clear"></div>


            </header>


            <div id="body" class="width">

                <section id="content" class="one-column">

                    <article>


                        <h2>REGISTER HERE</h2>
                        <div class="article-info"></div>

                        <form id="ContactForm" action="registerAction.jsp" onsubmit="return validatePass()" method="post">

                            <table style="border-bottom:thin">

                                <tr><td>Name:</td>
                                    <td><input pattern="^[A-Za-z]+" name="name" type="text" class="input" required=""/></td></tr>
                                <tr><td>PRIMARY NUM:</td>
                                    <td><input name="pno" type="tel" pattern="[789][0-9]{9}" class="input" /></td></tr>
                                <tr><td>SECONDARY NUM:</td>
                                    <td><input type="tel" pattern="[789][0-9]{9}" name="sno" class="input" /></td></tr>
                                <tr><td>email:</td>
                                    <td><input type="email" pattern="/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/; " class="input" name="email" /></td></tr>

                                <tr><td>username:</td>
                                    <td><input type="text" class="input" name="username" required="" /></td></tr>
                                <tr><td>password:</td>
                                    <td><input type="password" class="input" id="password" name="password" /></td></tr>
                                <tr><td>re enter password:</td>
                                    <td><input type="password" class="input" name="cpassword" id="cpassword"/></td></tr>

                            </table>


                            <input type="submit" class="button" value="REGISTER"/>
                            <input type="reset" class="button" value="CLEAR"/>

                        </form>

                    </article>


                </section>


                <div class="clear"></div>
            </div>

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

</div>
</footer>
</div>
</body>
</html>