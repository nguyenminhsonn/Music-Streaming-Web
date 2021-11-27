<%--
    Document   : MusicCRUDAdd
    Created on : 29-Oct-2021, 19:44:03
    Author     : nvlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            var maxField = 10; //Input fields increment limitation
            var addButton = $('.add_button'); //Add button selector
            var wrapper = $('.field_wrapper'); //Input field wrapper
            var fieldHTML = '<div style="display: inline-flex; margin-top: 5px; margin-bottom: 5px"><input id="id" name="id" value="" class="form-control input-lg" type="text" required><a href="javascript:void(0);" class="remove_button"><img style="margin: 5px ;width: 25px; height: 25px" src="https://cdn-icons-png.flaticon.com/512/61/61182.png"/></a></div>'; //New input field html
            var x = 1; //Initial field counter is 1
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
            background-image: url('img/7af86dbb9802d147c9f4be43665cdfc7.jpg');
            background-repeat: no-repeat;
            background-size: cover;
            height: 100%;
        }
    </style>
    <title >Add Song</title>
</head>
<body>
<div class="container-xl">
    <div class="mt-3">
        <a href="home"><button type="button" class="btn btn-success">Home</button></a>
        <a href="acrud"><button type="button" class="btn btn-danger">Artist Management</button></a>
        <a href="mcrud"><button type="button" class="btn btn-warning">Music Management</button></a>
        <a href="dashboard"><button type="button" class="btn btn-info">Dashboard</button></a>
    </div>
    <div class="mt-3">
        <form method="post" class="bg-light mx-auto col-10 col-md-9 col-lg-8 m-3" enctype="multipart/form-data">
            <div class="form-group">
                <input type="file" name="files[]" multiple>
                <input type="submit" value="Upload Files" name="submit">
            </div>
        </form>
<script>
    const url = "https://api.cloudinary.com/v1_1/longfpt/video/upload/?upload_preset=b1gxbprj";
    const form = document.querySelector("form");

    form.addEventListener("submit", (e) => {
        e.preventDefault();

        const files = document.querySelector("[type=file]").files;
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            let file = files[i];
            formData.append("file", file);
            formData.append("upload_preset", "docs_upload_example_us_preset");

            fetch(url, {
                method: "POST",
                body: formData
            })
                .then((response) => {
                    return response.text();
                })
                .then((data) => {
                    const object= JSON.parse(data)
                    document.getElementById("data").value = object.url;
                });
        }
    });

</script>
        <form class=" text-light form-horizontal mx-auto col-10 col-md-9 col-lg-8 m-3" action="addsong" method="POST">
            <fieldset>

                <!-- Form Name -->
                <legend class="text-light h3" style="font-weight: bold;">New Song</legend>

                <!-- Text input-->
                <div class="form-group">
                    <div class="col-md-4">
                        <img src="" id="image" style="width: 300px; height: 300px; background: wheat">
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="id">Song Name</label>
                    <div class="col-md-4">
                        <input style="width:500px" name="name" value="${requestScope.id}" class="form-control input-lg" type="text" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label " for="id">Artist</label>
                    <div class="col-md-5 field_wrapper">
                        <div style="display: inline-flex; margin-top: 5px; margin-bottom: 5px">
                            <select class="js-example-basic-multiple" name="states[]" style="width:500px" multiple="multiple">
                                <c:forEach items="${requestScope.singers}" var="singer">
                                    <option value="${singer.singerID}">${singer.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <a class="text-warning" href="acrud">Singer missing? Add a new one now</a>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="id">Genre</label>
                    <div class="col-md-4">
                        <select id="size" name="category">
                            <c:forEach items="${requestScope.categories}" var="cat">
                                <option value="${cat.categoryID}">${cat.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-md-4 control-label" for="num">Song URL</label>
                    <div class="col-md-10">
                        <input id="data" style="width:500px" name="song" class="form-control input-lg" type="text" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="num">Image URL</label>
                    <div class="col-md-4">
                        <input style="width:500px" id="img" name="img" class="form-control input-lg" type="text" required>
                    </div>
                </div>

                <!-- Button -->
                <div class="form-group">
                    <div class="col-md-4">
                        <button id="singlebutton" name="singlebutton" class="btn btn-primary">Add</button>
                    </div>
                </div>

            </fieldset>
        </form>
    </div>
</div>
</body>
</html>