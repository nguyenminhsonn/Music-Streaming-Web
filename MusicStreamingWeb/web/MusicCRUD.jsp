<%-- 
    Document   : MusicCRUD
    Created on : 27-Oct-2021, 23:06:59
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Artist CRUD</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/CRUD.css">
        <style>
            body{
                background: url('https://routenote.com/blog/wp-content/uploads/2020/10/Screenshot-2020-10-07-at-15.53.56-1280x589.png');
            }
        </style>
        <script>
            $(document).ready(function () {
                // Activate tooltip
                $('[data-toggle="tooltip"]').tooltip();

                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function () {
                    if (this.checked) {
                        checkbox.each(function () {
                            this.checked = true;
                        });
                    } else {
                        checkbox.each(function () {
                            this.checked = false;
                        });
                    }
                });
                checkbox.click(function () {
                    if (!this.checked) {
                        $("#selectAll").prop("checked", false);
                    }
                });
            });
        </script>
    </head>
    <body>
        <div class="container-xl">
            <div class="mt-3">
                <a href="home"><button type="button" class="btn btn-success">Home</button></a>
                <a href="acrud"><button type="button" class="btn btn-danger">Artist Management</button></a>
                <a href="mcrud"><button type="button" class="btn btn-warning">Music Management</button></a>
                <a href="dashboard"><button type="button" class="btn btn-info">Dashboard</button></a>
                <a href="userManage?op=1"><button type="button" class="btn btn-primary">Users Management</button></a>
            </div>
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2><b>Music</b> Management</h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="addsong"class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Add New Song</span></a>
                                <form class="form-inline my-2 my-lg-0 float-right" action="mcrud">
                                    <input class="form-control mr-sm-2" type="text" name="page" value="1" hidden="true">
                                    <input class="form-control mr-sm-2" type="search" placeholder="Enter Song Name" name="query" value="${requestScope.query}" aria-label="Search">
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Image</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.data}" var="song">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${song.songID}</td>
                                    <td>${song.name}</td>
                                    <td>
                                        <c:forEach items="${song.artist}" var="sg" varStatus="loop">
                                            ${sg.name}<c:if test="${!loop.last}">,</c:if>
                                        </c:forEach>
                                    </td>
                                    <td><img style="width: 75px; height: 75px" src="${song.img}" alt=""></td>
                                    <td>
                                        <a href="upsong?id=${song.songID}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="#deleteEmployeeModal" class="delete" data-toggle="modal" data-id="${song.songID}"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${requestScope.display}</b> out of <b>${requestScope.total}</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item ${requestScope.page==1?'disabled':''}"><a href="mcrud?page=${requestScope.page - 1}&query=${requestScope.query}" class="page-link">Previous</a></li>
                                <c:forEach begin="1" end="${requestScope.num}" var="i">
                                <li class="page-item ${requestScope.page == i ?'active':''}"><a href="mcrud?page=${i}&query=${requestScope.query}" class="page-link">${i}</a></li>
                                </c:forEach>
                            <li class="page-item ${requestScope.page==requestScope.num?'disabled':''}"><a href="mcrud?page=${requestScope.page + 1}&query=${requestScope.query}" class="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>        
        </div>
        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="delsong" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <p>Are you sure you want to delete this Artist?</p>
                            <p class="text-warning"><small>This action cannot be undone.</small></p>
                            <input type="text" id="id" name="id" readonly hidden="true">
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).on("click", ".delete", function () {
            var id = $(this).data('id');
            $(".modal-body #id").val(id);
        });
    </script>
</html>
