<%-- 
    Document   : Index
    Created on : 21-Sep-2021, 21:03:04
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/album.css">
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/mediaqueries.css">
        <link rel="stylesheet" href="css/hero.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/search.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <link rel = "icon" href ="./img/music.png" type = "image/x-icon">
        <title>Home</title>
    </head>
    <body>
        <%@include file="shared/header.jsp" %>

        <section class="hero">
            <div class="hero-container">
                <div class="hero-content">
                    <section id="slider">
                        <input type="radio" name="slider" id="s1" checked>
                        <input type="radio" name="slider" id="s2">
                        <input type="radio" name="slider" id="s3">
                        <input type="radio" name="slider" id="s4">
                        <input type="radio" name="slider" id="s5">

                        <label for="s1" id="slide1"></label>
                        <label for="s2" id="slide2"></label>
                        <label for="s3" id="slide3"></label>
                        <label for="s4" id="slide4"></label>
                        <label for="s5" id="slide5"></label>
                    </section>

                </div>
            </div>
        </section>
        <!-- GENRES -->
        <div class="my_preview">
            <div class="preview_body">
                <div class="side_bar">
                    <!-- Single Album  -->
                    <div style="margin-left:20px"> 
                        <span  style="font-size:x-large;font-weight:bolder">New Music</span>
                        <!-- box -->
                        <div style="display: flex;justify-content: space-between;">
                    <c:forEach items="${requestScope.latest}" var="song">
                            <div style="height:170px;width: 200px;display:flex;flex-direction:column;margin-top:20px">
                                <img src="${song.img}" alt="" style="width: 170px;height:200px">
                                <div style="display: flex;
                                     flex-direction: column;
                                     align-items: center; font-weight:bolder;font-size:larger;margin-top:3%;margin-right: 13% ">
                                    <a href="player?songID=${song.songID}"><span>${song.name}</span></a>
                                    <c:forEach items="${song.artist}" var="sg" varStatus="loop">
                                        <a href="artist?aid=${sg.singerID}" style="color:rgba(210,210,210,0.89)">${sg.name}<c:if test="${!loop.last}">,</c:if> </a>
                                    </c:forEach>
                                </div>
                            </div>
                    </c:forEach>
                        </div>
                    </div>  
                    <!-- Topic   -->
                    <div style="display: flex;margin-top:7%;flex-direction:column" >
                        <div style="display: flex;justify-content: space-between">
                            <span  style="font-size:x-large;font-weight:bolder">Genres</span>
                            <a href="category" style="text-decoration: underline;font-size: medium" >View all</a>
                        </div>
                        <div style="display: flex;">
                    <c:forEach items="${requestScope.top4}" var="genres">
                            <div style="width: 270px;height:200px">
                                <a href="category?categoryID=${genres.categoryID}"> <img src="${genres.img}" alt="" style="border-radius:5%;width: 260px;height:140px"></a>
                            </div>
                    </c:forEach>
                        </div>

                    </div>

                    <div style="margin-top:7%;">
                        <span  style="font-size:x-large;font-weight:bolder">It's a Hit</span>
                        <div class="grid-container">
                <c:forEach var="song" items="${requestScope.top8}">
                            <div class="item1">
                                <a href="player?songID=${song.songID}"><img src="${song.img}" alt=""></a>
                                <div style="display:flex;flex-direction:column;justify-content: center;padding:10px">
                                    <span style="font-size: large;font-weight: 700"><a href="player?songID=${song.songID}">${song.name}</a></span>
                                    <c:forEach items="${song.artist}" var="sg" varStatus="loop">
                                        <a href="artist?aid=${sg.singerID}" style="font-size:12px;font-weight: 400">${sg.name}<c:if test="${!loop.last}">,</c:if> </a>
                                    </c:forEach>
                                </div>
                            </div>
                </c:forEach>
                        </div>
                    </div>


                    <!-- RElated Artist  -->
                    <div style="margin:20px; margin-top:7%"> 
                        <span  style="font-size:x-large;font-weight:bolder">WEEKLY ARTIST CHART</span>
                        <!-- box -->
                        <div style="display: flex;justify-content: space-between;">
                         <c:forEach var="artist" items="${requestScope.wArtist}">
                            <div style="height:300px;width: 250px;background:rgba(255, 255, 255, 0.1);display:flex;flex-direction:column;">
                                <a href="artist?aid=${artist.singerID}"><img src="${artist.img}" alt="" style="border-radius:50%;width: 250px;height:250px;padding:10px;"></a>
                                <div style="display: flex;
                                     flex-direction: column;
                                     align-items: center; font-weight:bolder;font-size:x-large">
                                    <a href="artist?aid=${artist.singerID}"><span>${artist.name}</span></a>
                                </div>
                            </div>
                         </c:forEach>
                        </div>
                    </div> 
                </div>

                <div class="my_body">
                    <h1>Top songs</h1>
                    <iframe src="https://open.spotify.com/embed/playlist/37i9dQZF1DXcBWIGoYBM5M" width="300" height="450" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
                    <h1>Top Album</h1>
                    <iframe src="https://open.spotify.com/embed/playlist/37i9dQZF1DWYs83FtTMQFw" width="300" height="400" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
                    <h1>Top Podcast</h1>
                    <iframe src="https://open.spotify.com/embed/show/7Eo5RqrItEwmuwSJv5n6QZ" width="300" height="300" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
                    <iframe src="https://open.spotify.com/embed/show/0X0b4Wb7cgvs7UCVSBaqGo" width="300" height="300" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
                </div>
            </div>
        </div>

        <%@include file="shared/footer.jsp" %>
    </body>
</html>
