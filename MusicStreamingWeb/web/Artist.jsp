<%-- 
    Document   : Artist
    Created on : 15-Oct-2021, 11:15:01
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="css/artist.css">
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/header.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .login_button {
                color: white; 
                font-weight: bold; 
                font-size: medium
            }

            .login_button:hover {
                color: blue;
            }
        </style>
        <title>Artist Page</title>
    </head>

    <body>
        <div class="player">
            <div class="player_body">
                <%@include file="shared/sidebar.jsp" %>
                <div class="colection_body">
                    <div class="collection_header">
                        <div class="header_left">
                            <a href="javascript: history.go(-1)">
                                <i class="fas fa-chevron-circle-left fa-3x" style="color:black"></i>
                            </a>
                        </div>
                        <div class="header_right">
                            <c:if test="${sessionScope.account==null}" >
                                <a href="login" class="login_button">Login Now!</a>
                            </c:if>
                            <c:if test="${sessionScope.account!=null}" >
                                <!-- Profile menu -->
                                <div class="profile-container">
                                    <input type="checkbox" class="dropdown-menu-toggler" />
                                    <div class="dropdown-menu">
                                        <ul> 
                                            <li><a href="profile">Account</a></li>
                                            <li><a href="logout">Log out</a></li>
                                        </ul>
                                    </div>
                                    <div class="profile">
                                        <svg viewBox="0 0 1024 1024" class="profile-icon">
                                        <path
                                            d="M730.06 679.64q-45.377 53.444-101.84 83.443t-120 29.999q-64.032 0-120.75-30.503t-102.6-84.451q-40.335 13.109-77.645 29.747t-53.948 26.722l-17.142 10.084Q106.388 763.84 84.96 802.41t-21.428 73.107 25.461 59.242 60.754 24.705h716.95q35.293 0 60.754-24.705t25.461-59.242-21.428-72.603-51.679-57.225q-6.554-4.033-18.907-10.84t-51.427-24.453-79.409-30.755zm-221.84 25.72q-34.285 0-67.561-14.873t-60.754-40.335-51.175-60.502-40.083-75.124-25.461-84.451-9.075-87.728q0-64.032 19.915-116.22t54.452-85.964 80.67-51.931 99.072-18.151 99.072 18.151 80.67 51.931 54.452 85.964 19.915 116.22q0 65.04-20.167 130.58t-53.948 116.72-81.426 83.443-98.568 32.268z">
                                        </path>
                                        </svg>
                                    </div>
                                    <ul>
                                        <li>${sessionScope.account.username}</li>
                                        <li>
                                            <svg viewBox="0 0 1024 1024" class="profile-arrow">
                                            <path
                                                d="M476.455 806.696L95.291 425.532Q80.67 410.911 80.67 390.239t14.621-34.789 35.293-14.117 34.789 14.117L508.219 698.8l349.4-349.4q14.621-14.117 35.293-14.117t34.789 14.117 14.117 34.789-14.117 34.789L546.537 800.142q-19.159 19.159-38.318 19.159t-31.764-12.605z">
                                            </path>
                                            </svg>
                                        </li>
                                    </ul>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="body_info">
                        <img
                            src="${requestScope.singer.img}" />
                        <div class="body_info_text">
                            <span style="display: flex;"><img src="./correct.png" alt="" style="height:20px"> <p> Nghệ sĩ được xác minh</p> </span> 
                            <h2>${requestScope.singer.name}</h2>
                            <p>340.547 người nghe hàng tháng</p>
                        </div>
                    </div>
                    <div class="body_songs">
                        <c:if test="${sessionScope.account!=null}" >
                            <div class="body_song_icon play_button" style="display: flex;align-items: center;">
                                <i class="fas fa-play-circle fa-5x" id="playlist_body_suffle" style="color: green;"></i>
                                <i class="fas fa-pause-circle fa-5x my pause" id="playlist_body_suffle"></i>
                                <span style="font-size: larger;font-weight:bolder;padding:10px;border:1px solid gray;margin-right:20px">THEO DÕI</span>
                                <i class="fas fa-ellipsis-h fa-2x" style="color: gray;"></i>
                            </div>
                        </c:if>
                        <div class="playlist_song" style="margin-top: 20px;">
                        </div>
                        <div style="display: flex;flex-direction:column">
                            <span  style="font-size:x-large; margin-left:20px;font-weight:bolder">Bản phát hành nổi tiếng</span>
                        </div>
                        <!-- Album -->
                        <div style="margin: 20px;display:flex;justify-content: space-between;">
                            <c:forEach items="${requestScope.songs}" var="song">
                                <!-- box -->
                                <div style="height:300px;width: 230px;background:rgba(255, 255, 255, 0.1);display:flex;flex-direction:column">
                                    <img src="${song.img}" alt="" style="width: 230px;height:250px;padding:10px;">
                                    <div style="display: flex;
                                         flex-direction: column;
                                         align-items: center; font-weight:bolder;font-size:larger">
                                        <span><a href="player?songID=${song.songID}">${song.name}</a></span>
                                        <span>
                                            <c:forEach items="${song.artist}" var="sg" varStatus="loop">
                                                <a href="artist?aid=${sg.singerID}">${sg.name}<c:if test="${!loop.last}">,</c:if> </a>
                                            </c:forEach>
                                        </span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>


                    <!-- Related Artist  -->
                    <div style="margin:20px;"> 
                        <span  style="font-size:x-large;font-weight:bolder">RELATED ARTIST</span>
                        <!-- box -->
                        <div style="display: flex;justify-content: space-between;">

                            <c:forEach items="${requestScope.relateds}" var="sg">
                                <!-- box -->
                                <div style="height:300px;width: 250px;background:rgba(255, 255, 255, 0.1);display:flex;flex-direction:column;">
                                    <img src="${sg.img}" alt="" style="border-radius:50%;width: 250px;height:250px;padding:10px;">
                                    <div style="display: flex;
                                         flex-direction: column;
                                         align-items: center; font-weight:bolder;font-size:larger">
                                        <span><a href="artist?aid=${sg.singerID}">${sg.name}</a></span>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div> 



                    <div style="margin:20px;display:flex;flex-direction:column"> 
                        <span  style="font-size:x-large;font-weight:bolder">GIỚI THIỆU</span>
                        <img src="https://i.scdn.co/image/4edc74079bee330c2b0569d319427018ca40cd3a" alt="" style="width:90%;height:500px" >
                        <p style="margin-top:-10%;">${requestScope.singer.info}</p>
                    </div> 
                </div>
            </div>

        </div>
    </body>
</html>
