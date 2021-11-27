<%-- 
    Document   : MusicCRUDUpdate
    Created on : 29-Oct-2021, 23:06:35
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('.js-example-basic-multiple').select2();
            });

            $(document).on('input', '#img', function () {
                $("#image").attr("src", $("#img").val());
            });
        </script>
        <style>
            body{
                background: url('https://routenote.com/blog/wp-content/uploads/2020/10/Screenshot-2020-10-07-at-15.53.56-1280x589.png');
            }
            .form-horizontal{
                border: solid black; 
                background: url('https://wallpapercave.com/wp/wp499258.jpg');
            }
        </style>
        <title>Update Song</title>
    </head>
    <body>
        <div>

        </div>
        <div class="container-xl">
            <div class="mt-3">
                <a href="home"><button type="button" class="btn btn-success">Home</button></a>
                <a href="acrud"><button type="button" class="btn btn-danger">Artist Management</button></a>
                <a href="mcrud"><button type="button" class="btn btn-warning">Music Management</button></a>
                <a href="dashboard"><button type="button" class="btn btn-info">Dashboard</button></a>
            </div>
            <div>
                <form class="form-horizontal mx-auto col-10 col-md-9 col-lg-8 m-3" action="upsong?id=${requestScope.song.songID}" method="POST">
                    <fieldset>

                        <!-- Form Name -->
                        <legend>New Song</legend>

                        <!-- Text input-->
                        <div class="form-group">
                            <div class="col-md-4">
                                <img src="${requestScope.song.img}" id="image" style="width: 300px; height: 300px; background: wheat">
                            </div>
                        </div>

                        <!-- Text input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="id">Song Name</label>  
                            <div class="col-md-4">
                                <input style="width:500px" name="name" value="${requestScope.song.name}" class="form-control input-lg" type="text" required>
                            </div>
                        </div>
                            
                                <c:set var = "artists" value = "${requestScope.artists}"/>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="id">Artist</label>  
                            <div class="col-md-5 field_wrapper">
                                <div style="display: inline-flex; margin-top: 5px; margin-bottom: 5px">
                                    <select class="js-example-basic-multiple" name="states[]" style="width:500px" multiple="multiple">
                                        <c:forEach items="${requestScope.singers}" var="singer">
                                            <c:set var = "ID" value = "*${singer.singerID}*"/>
                                            <option value="${singer.singerID}" ${fn:contains(artists, ID)?'selected':''} >${singer.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <a href="acrud">Singer missing? Add a new one now</a>
                            </div>
                        </div>
                            
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="id">Genre</label>  
                            <div class="col-md-4">
                                <select id="size" name="category">
                                    <c:forEach items="${requestScope.categories}" var="cat">
                                        <option value="${cat.categoryID}" ${requestScope.song.genre == cat.categoryName?'selected':''}>${cat.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-md-4 control-label" for="num">Song URL</label>  
                            <div class="col-md-4">
                                <input style="width:500px" name="song" value="${requestScope.song.uri}" class="form-control input-lg" type="text" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="num">Image URL</label>  
                            <div class="col-md-4">
                                <input style="width:500px" id="img" name="img" value="${requestScope.song.img}" class="form-control input-lg" type="text" required>
                            </div>
                        </div>

                        <!-- Button -->
                        <div class="form-group">
                            <div class="col-md-4">
                                <button id="singlebutton" name="singlebutton" class="btn btn-primary">Update</button>
                            </div>
                        </div>

                    </fieldset>
                </form>
            </div>        
        </div>
    </body>
</html>
