<%-- 
    Document   : Header
    Created on : 21-Nov-2014, 1:47:42 PM
    Author     : meenakshimehta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Online Drug Store</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="shortcut icon" href="images/favicon.ico" >
        <script type="text/javascript" src="../Scripts/JScript.js"></script>
        <script type="text/javascript" src="../Scripts/javascriptFile.js"></script>
        <script src="../Scripts/jquery.slides.min.js" type="text/javascript"></script>

        <script>
            $(function() {

                $("#slides").slidesjs({
                    play: {
                        active: true,
                        // [boolean] Generate the play and stop buttons.
                        // You cannot use your own buttons. Sorry.
                        effect: "slide",
                        // [string] Can be either "slide" or "fade".
                        interval: 5000,
                        // [number] Time spent on each slide in milliseconds.
                        auto: true,
                        // [boolean] Start playing the slideshow on load.
                        swap: false,
                        // [boolean] show/hide stop and play buttons
                        pauseOnHover: false,
                        // [boolean] pause a playing slideshow on hover
                        restartDelay: 2500
                                // [number] restart delay on inactive slideshow
                    },
                    navigation: {
                        active: false,
                        // [boolean] Generates next and previous buttons.
                        // You can set to false and use your own buttons.
                        // User defined buttons must have the following:
                        // previous button: class="slidesjs-previous slidesjs-navigation"
                        // next button: class="slidesjs-next slidesjs-navigation"
                        effect: "slide"
                                // [string] Can be either "slide" or "fade".
                    }
                });
                $(".slidesjs-pagination").hide();
            });
        </script>

        <style>
            .error {color: red;}
        </style>

    </head>
    <body>
        <div id="header">
            <a href=""><img src="/images/logo.png" width="237" height="123" class="float" alt="setalpm" /></a>																																																																	
            <div class="topnav">
               
            </div>
            <ul id="menu">
                <br/>
                <li><a href="" style="background-image:url(/images/tab.gif);">s</a></li>
                <li><a href="">Product</a></li>
                <li><a href="">Order</a></li>
               
            </ul>
        </div>

