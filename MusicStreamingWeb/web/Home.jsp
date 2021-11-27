<%-- 
    Document   : Home
    Created on : 01-Oct-2021, 17:00:13
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/player.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/open.css">
    <link rel="stylesheet" href="css/header.css">
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

    </style>
    <title>Open</title>
</head>
<body>
<div class="player">
    <div class="player_body">
        <%@include file="shared/sidebar.jsp" %>
        <div class="colection_body" style="background: linear-gradient(rgb(187, 81, 81),black);">
            <div class="collection_header">
                <div class="header_left">

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
                                        <li><a href="acrud?page=1&query=" style="color: black">CRUD</a></li>
                                        <li><a href="userManage?op=1" style="color: black">User Management</a></li>
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


            <h1 style="font-size:xx-large;padding:20px 0">Hope you enjoy<c:if test="${sessionScope.account != null}">, ${sessionScope.account.username}</c:if></h1>
            <h1 style="font-size:x-large;padding:20px 0">What's Hot?</h1>
            <div class="large-boxes" style="display:grid; grid-template-columns: auto auto auto auto; grid-gap: 10px;">
                <c:forEach items="${requestScope.top8}" var="song">
                    <!-- box -->
                    <div style="margin:10px;height:300px;width: 230px;background:rgba(255, 255, 255, 0.2);display:flex;flex-direction:column">
                        <img src="${song.img}" alt="" style="width: 230px;height:250px;padding:10px;">
                        <div style="display:flex">
                            <div style="height: 48px;width: 45px;background: green;margin-left:10px;margin-top: -30px;display: flex;border-radius: 50%;justify-content: center;align-items: center">
                                <a href="player?songID=${song.songID}"><i class="fas fa-play fa-2x" ></i></a>
                            </div>
                            <p style="font-size: smaller; font-weight: normal">Like: ${song.likeCount}</p>
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
            </div>





            <div class="middle_section">
                <h1 style="font-size:x-large;padding:20px 0">What's New?</h1>
                <div class="large-boxes" style="display:grid; grid-template-columns: auto auto auto auto; grid-gap: 10px;">
                    <c:forEach items="${requestScope.latest}" var="song">
                        <!-- box -->
                        <div style="margin:10px;height:300px;width: 230px;background:rgba(255, 255, 255, 0.2);display:flex;flex-direction:column">
                            <img src="${song.img}" alt="" style="width: 230px;height:250px;padding:10px;">
                            <div style="display:flex">
                                <div style="height: 48px;width: 45px;background: green;margin-left:10px;margin-top: -30px;display: flex;border-radius: 50%;justify-content: center;align-items: center">
                                    <a href="player?songID=${song.songID}"><i class="fas fa-play fa-2x" ></i></a>
                                </div>
                                <p style="font-size: smaller; font-weight: normal">Like: ${song.likeCount}</p>
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
                </div>

            </div>



            <h1 style="font-size:x-large;padding:20px 0">Latest From Your Favorite Artist</h1>
            <div class="boxes">
                <c:forEach var="song" items="${requestScope.top8}">
                    <!-- box -->
                    <div style="margin:10px;height:300px;width: 230px;background:rgba(255, 255, 255, 0.2);display:flex;flex-direction:column">
                        <img src="${song.img}" alt="" style="width: 230px;height:250px;padding:10px;">
                        <div style="display:flex">
                            <div style="height: 48px;width: 45px;background: green;margin-left:10px;margin-top: -30px;display: flex;border-radius: 50%;justify-content: center;align-items: center">
                                <a href="player?songID=${song.songID}"><i class="fas fa-play fa-2x" ></i></a>
                            </div>
                            <p style="font-size: smaller; font-weight: normal">Like: ${song.likeCount}</p>
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
            </div>
        </div>
    </div>
</div>
</body>
</html>