<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : UserManage
    Created on : Oct 31, 2021, 4:02:27 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Users Management</title>
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
                <a href="userManage?op=1"><button type="button" class="btn btn-danger">All Users</button></a>
                <a href="userManage?op=2"><button type="button" class="btn btn-warning">Premium Users</button></a>
                <a href="userManage?op=3"><button type="button" class="btn btn-info">Normal Users</button></a>
                <a href="mcrud"><button type="button" class="btn btn-primary">Music Management</button></a>
            </div>
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2><b>Users</b> Management</h2>
                            </div>
                            <div class="col-sm-6">
                                <!--                                <a href="addsong"class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Add New Song</span></a>-->
                                <form class="form-inline my-2 my-lg-0 float-right" action="userManage">
                                    <input class="form-control mr-sm-2" type="text" name="page" value="1" hidden="true">
                                    <input class="form-control mr-sm-2" type="text" name="op" value="${requestScope.op}" hidden="true">
                                    <input class="form-control mr-sm-2" type="search" placeholder="Enter User Name" name="query" value="${requestScope.query}" aria-label="Search">
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <!--                                <th>
                                                                    <span class="custom-checkbox">
                                                                        <input type="checkbox" id="selectAll">
                                                                        <label for="selectAll"></label>
                                                                    </span>
                                                                </th>-->
                                <th></th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Registration Date</th>
                                <th>Role</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.data}" var="user">
                                <tr>
                                    <!--                                    <td>
                                                                            <span class="custom-checkbox">
                                                                                <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                                                                <label for="checkbox1"></label>
                                                                            </span>
                                                                        </td>-->
                                    <c:if test="${user.status == 'premium' && requestScope.udb.getActive(user.username) == true}">
                                        <td><img width="80px" src="https://www.shareicon.net/data/512x512/2017/02/15/878685_user_512x512.png"/></td>
                                        </c:if>
                                        <c:if test="${user.status != 'premium' && requestScope.udb.getActive(user.username) == true}">
                                        <td><img width="80px" src="https://image.pngaaa.com/826/4806826-middle.png"/></td>
                                        </c:if>
                                        <c:if test="${requestScope.udb.getActive(user.username) == false}">
                                        <td><img width="80px" src="https://previews.123rf.com/images/asmati/asmati1610/asmati161000233/63831576-user-sign-illustration-white-icon-on-red-circle-.jpg"/></td>
                                        </c:if>
                                    <td>${user.fullname}</td>
                                    <td>${user.email}</td>
                                    <td>${requestScope.udb.getRegisDate(user.username)}</td>
                                    <td>
                                        <c:if test="${requestScope.udb.getRole(user.username) != 'admin'}">
                                            User
                                        </c:if>
                                        <c:if test="${requestScope.udb.getRole(user.username) == 'admin'}">
                                            Admin
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${requestScope.udb.getActive(user.username) == true}">
                                            ${user.status.toUpperCase()}
                                        </c:if>
                                        <c:if test="${requestScope.udb.getActive(user.username) == false}">
                                            <div style="color:red; font-size: 15px; font-weight: bold">Banned</div>
                                        </c:if>
                                    </td>

                                    <td>
                                        <c:if test="${requestScope.udb.getActive(user.username) == true && requestScope.udb.getRole(user.username) != 'admin'}">
                                            <a href="#banUserModal" class="delete" data-toggle="modal" data-id="${user.username}"><i class="material-icons" data-toggle="tooltip" title="Ban">&#xE99A;</i></a>
                                        </c:if>
                                        <c:if test="${requestScope.udb.getActive(user.username) == false && requestScope.udb.getRole(user.username) != 'admin'}">
                                            <a href="#banUserModal" class="delete" data-toggle="modal" data-id="${user.username}"><i class="material-icons" data-toggle="tooltip" title="Unban">&#xe86c;</i></a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${requestScope.display}</b> out of <b>${requestScope.total}</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item ${requestScope.page==1?'disabled':''}"><a href="userManage?page=${requestScope.page - 1}&query=${requestScope.query}&op=${requestScope.op}" class="page-link">Previous</a></li>
                                <c:forEach begin="1" end="${requestScope.num}" var="i">
                                <li class="page-item ${requestScope.page == i ?'active':''}"><a href="userManage?page=${i}&query=${requestScope.query}&op=${requestScope.op}" class="page-link">${i}</a></li>
                                </c:forEach>
                            <li class="page-item ${requestScope.page==requestScope.num?'disabled':''}"><a href="userManage?page=${requestScope.page + 1}&query=${requestScope.query}&op=${requestScope.op}" class="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>        
        </div>
        <!-- Delete Modal HTML -->
        <div id="banUserModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="ban" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Ban User</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure about that?</p>
                            <!--                            <p class="text-warning"><small>This action cannot be undone.</small></p>-->
                            <input type="text" id="id" name="id" readonly hidden="true">
                            <input type="text" name="op" value="${requestScope.op}" hidden="true">
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" value="Yes">
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
