<%-- 
    Document   : Dashboard
    Created on : 09-Nov-2021, 13:48:39
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/Dashboard.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
        <title>DashBoard</title>
    </head>
    <body>
        <div class="container">

            <div class="main">
                <div class="mt-3">
                    <a href="home"><button type="button" class="btn btn-success">Home</button></a>
                    <a href="acrud"><button type="button" class="btn btn-danger">Artist Management</button></a>
                    <a href="mcrud"><button type="button" class="btn btn-warning">Music Management</button></a>
                    <a href="dashboard"><button type="button" class="btn btn-info">Dashboard</button></a>
                </div>
                <div class="overview">
                    <h2>Overview</h2>
                    <div class="collection">
                        <!-- box  -->
                        <div class="box" style="background-color: rgb(236, 140, 140);" id="box1">
                            <img src="./img/user.png" alt="">
                            <div class="number">
                                <p>Total user</p>
                                <h1>${totalUser}</h1>
                            </div>
                        </div>
                        <!-- box -->
                        <div class="box" style="background-color: rgb(236, 213, 140);" id="box2">
                            <img src="./img/transaction.png" alt="">
                            <div class="number">
                                <p>Revenue This Month</p>
                                <h1>${totalUserPre}$</h1>
                            </div>
                        </div>
                        <div class="chart-canvas">
                            <canvas id="myChart1" width="600" height="200"></canvas>
                        </div>
                    </div>
                </div>
                <div class="hero">
                    <div class="second">
                        <div class="monthlyearning">
                            <h2 style="margin-top: -10px;">Monthly Earning</h2>
                            <canvas id="monthlyearning"
                                    style="display: block;box-sizing: border-box;height:250px;width:350px"></canvas>
                        </div>
                        <div class="thismonth">
                            <h3 style="color: gray;">This Month</h3>
                            <h1 style="margin-top: -20px;">${totalUserThisMonth}</h1>
                            <canvas id="thismonth" width="300" height="200"></canvas>
                        </div>
                        <div class="lastmonth">
                            <h3 style="color: gray;">Last Month</h3>
                            <h1 style="margin-top: -20px;">${totalUserLastMonth}</h1>
                            <canvas id="lastmonth" width="300" height="200"></canvas>
                        </div>
                    </div>
                </div>
                <div class="overview2">
                    <div class="recenttransaction">
                        <h2 style="margin-top: 30px;margin-left:20px;color:black">Lastest Transaction</h2>
                        <table>
                            <tr>
                                <th>id</th>
                                <th>Name</th>
                                <th>Date</th>
                                <th>Amount</th>
                                <th>Status</th>
                            </tr>
                            <c:forEach items="${list}" var="p">
                                <tr>          
                                    <td>${p.getBillID()}</td>
                                    <td>${p.getUsername()}</td>
                                    <td>${p.getDate()}</td>
                                    <td>${p.getAmount()}</td>
                                    <td>Active</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="Analysis">
                        <h3 style="margin-left: 30px;color:white">Song Analysis</h3>
                        <div class="artis_analysis">
                            <canvas id="artist_ana" width="300" height="200"></canvas>
                        </div>
                        <div class="cateanaysis">
                            <canvas id="cate_ana" width="300" height="200"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        // Chart 1
        var ctx = document.getElementById('myChart1').getContext('2d');
        var mixedChart = new Chart(ctx, {
            type: 'bar',
            data: {
                datasets: [{
                        label: 'Total User',
                        data: [${userMonth[0]},${userMonth[1]},${userMonth[2]},
        ${userMonth[3]},${userMonth[4]},${amountMonth[5]},
        ${userMonth[6]},${userMonth[7]},${userMonth[8]},${userMonth[9]},${userMonth[10]},${userMonth[11]}],
                        backgroundColor: '#009500'
                    }],
                labels: ['Jan', 'Ferb', 'Mar', 'Apr', 'May', 'Jun', 'July', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            options: {}
        });
        //Chart2
        var ctx = document.getElementById('monthlyearning').getContext('2d');
        var mixedChart = new Chart(ctx, {
            type: 'line',
            data: {
                datasets: [{
                        label: 'Revenue',
                        data: [${amountMonth[0]},${amountMonth[1]},${amountMonth[2]},${amountMonth[3]},${amountMonth[4]},${amountMonth[5]},
        ${amountMonth[6]},${amountMonth[7]},${amountMonth[8]},${amountMonth[9]},${amountMonth[10]},${amountMonth[11]}],
                        backgroundColor: 'rgb(221, 214, 120)',
                        borderColor: 'rgb(221, 214, 120)',
                    }],
                labels: ['Jan', 'Ferb', 'Mar', 'Apr', 'May', 'Jun', 'July', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            options: {}
        });
        //Chart this Month
        var ctx = document.getElementById('thismonth').getContext('2d');
        var mixedChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                datasets: [{
                        label: 'Total User',
                        data: [${totalPremiumUserThisMonth},${totalNormalUserThisMonth}],
                        backgroundColor: [
                            'rgb(255, 99, 132)',
                            'rgb(54, 162, 235)',
                            'rgb(255, 205, 86)'
                        ],
                        hoverOffset: 4,
                    }],
                labels: ['Premiun User', 'Normal User']
            },
            options: {}
        });
        //Chart last Month
        var ctx = document.getElementById('lastmonth').getContext('2d');
        var mixedChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                datasets: [{
                        label: 'Total User',
                        data: [${totalPremiumUserLastMonth},${totalNormalUserLastMonth}],
                        backgroundColor: [
                            'rgb(255, 99, 132)',
                            'rgb(54, 162, 235)',
                            'rgb(255, 205, 86)'
                        ],
                        hoverOffset: 4,
                    }],
                labels: ['Premiun User', 'Normal User']
            },
            options: {}
        });
        //Artist Analysis
        var ctx = document.getElementById('artist_ana').getContext('2d');
        var mixedChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: [${numberSong.getSongID()[1]},${numberSong.getSongID()[2]},${numberSong.getSongID()[3]},${numberSong.getSongID()[4]},${numberSong.getSongID()[5]}],
                datasets: [{
                        label: 'Number of listen',
                        data: [${numberSong[1]}, ${numberSong[2]}, ${numberSong[3]}, ${numberSong[4]}, ${numberSong[5]}],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 205, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(201, 203, 207, 0.2)'
                        ],
                        borderColor: [
                            'rgb(255, 99, 132)',
                            'rgb(255, 159, 64)',
                            'rgb(255, 205, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(54, 162, 235)',
                            'rgb(153, 102, 255)',
                            'rgb(201, 203, 207)'
                        ],
                        borderWidth: 1

                    }],
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            },
        });
        //Cate Analysis
        var ctx = document.getElementById('cate_ana').getContext('2d');
        var mixedChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Binz', 'Binz', 'Binz', 'Binz', 'Binz'],
                datasets: [{
                        label: 'Number of listen',
                        data: [65, 59, 80, 81, 56, 55, 40],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(255, 159, 64, 0.2)',
                            'rgba(255, 205, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(201, 203, 207, 0.2)'
                        ],
                        borderColor: [
                            'rgb(255, 99, 132)',
                            'rgb(255, 159, 64)',
                            'rgb(255, 205, 86)',
                            'rgb(75, 192, 192)',
                            'rgb(54, 162, 235)',
                            'rgb(153, 102, 255)',
                            'rgb(201, 203, 207)'
                        ],
                        borderWidth: 1

                    }],
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            },
        });
        //Lighr Dark
        const chk = document.getElementById('chk');
        chk.addEventListener('change', () => {
            document.body.classList.toggle('dark');
        });
    </script>
    <!--    <body>
            <form method="get" action="">
                <table class="table">
                    
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Date</th>
                            <th>Amount</th>
                            <th>Status</th>
                        </tr>
              
                 
    <c:forEach items="${list}" var="p">
        <tr>          
            <td>${p.getBillID()}</td>
            <td>${p.getUsername()}</td>
            <td>${p.getDate()}</td>
            <td>${p.getAmount()}</td>
            <td>Active</td>
        </tr>
    </c:forEach>

</table>
</form>
</body>-->
</html>
