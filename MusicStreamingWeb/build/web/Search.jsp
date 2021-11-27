<%-- 
    Document   : Search
    Created on : 22-Sep-2021, 10:52:34
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link rel="stylesheet" href="css/player.css">
        <link rel="stylesheet" href="css/base.css">
        <link rel="stylesheet" href="css/open.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel = "icon" href ="./img/music.png" type = "image/x-icon">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" asp-append-version="true" />
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
            #myInput,select:focus{
                outline: none;
            }
            select{
                margin-right: -20px;
                border: none;
                width: 80px;
                height: 20px;
                font-size: medium;
            } </style>
        <title>Search</title>
    </head>
    <body>
        <div class="player_body">
            <%@include file="shared/sidebar.jsp" %>

            <div class="colection_body" style="background: linear-gradient(130deg,#a82654,#fd8c69);">
                <div class="collection_header" style="margin-bottom: 30px;">
                    <div class="header_left" style="background-color: white; color:gray;border-radius:30px;display:flex;align-items:center;width:90%;padding:3px">
                        <form action="search" style="display: flex;padding: 10px 0">
                            <button type="submit" class="fa fa-search fa-2x" style="margin:0 10px;border: none;background: white;cursor: pointer"></button>
                            <input type="text" placeholder="Search for Artists,Songs, or Playlist" size="120px" style="border: none;" value="${requestScope.query}" id="myInput" name="query" >
                            <div style="border-left: 2px solid black">
                                <select name="type" id="type" onchange="this.form.submit()" style="margin: 0 7px; border: none">
                                    <option value="song" ${requestScope.type=='song'?'selected':''}>Song</option>
                                    <option value="artist" ${requestScope.type=='artist'?'selected':''}>Artist</option>
                                </select>
                            </div>

                        </form>
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
                                            <c:if test="${sessionScope.account.role == 'admin'}">
                                                <li><a href="#" style="color: black">Dashboard</a></li>
                                                <li><a href="acrud?page=1&query=" style="color: black">Music CRUD</a></li>
                                            </c:if>
                                            <li><a href="profile" style="color: black">Account</a></li>
                                            <li><a href="logout" style="color: black">Log out</a></li>
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
                <h1 style="font-size:xx-large;padding:20px 0">Search Results:</h1>
                <h3>${requestScope.error}</h3>
                <div class="large-boxes" style="display:grid; grid-template-columns: auto auto auto auto; grid-gap: 15px;">
                    <c:forEach items="${requestScope.slist}" var="song">
                        <!-- box -->
                        <div style="height:300px;width: 230px;background:rgba(255, 255, 255, 0.2);display:flex;flex-direction:column">
                            <img src="${song.img}" alt="" style="width: 230px;height:250px;padding:10px;">
                            <div style="height: 48px;width: 45px;background: green;margin-left:10px;margin-top: -30px;display: flex;border-radius: 50%;justify-content: center;align-items: center">
                                <a href="player?songID=${song.songID}"><i class="fas fa-play fa-2x" ></i></a>
                            </div>
                            <div style="display: flex;
                                         flex-direction: column;
                                         align-items: center; font-weight:bolder;font-size:larger;margin: 10px 0">
                                <span style="font-size: large;font-weight: 700"><a href="player?songID=${song.songID}">${song.name}</a></span>
                                <span>
                                    <c:forEach items="${song.artist}" var="sg" varStatus="loop">
                                        <a href="artist?aid=${sg.singerID}" >${sg.name}<c:if test="${!loop.last}">,</c:if> </a>
                                    </c:forEach>
                                </span>
                            </div>
                        </div>
                    </c:forEach>
                    <c:forEach items="${requestScope.alist}" var="singer">
                    <div style="display: flex;justify-content: space-between;">
                        <div style="height:300px;width: 250px;background:rgba(255, 255, 255, 0.2);display:flex;flex-direction:column;margin: 10px">
                            <img src="${singer.img}" alt="" style="border-radius:50%;width: 250px;height:250px;padding:10px;">
                            <div style="display: flex;
                                     flex-direction: column;
                                     align-items: center; font-weight:bolder;font-size:larger;padding: 10px 0">
                                <a href="artist?aid=${singer.singerID}" style="font-size: x-large"><span>${singer.name}</span></a>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>

        </div>

    </body>
</html>
