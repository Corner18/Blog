<#ftl encoding="UTF-8"/>
<#include "base.ftl"/>
<#macro content>

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Blog Post - Start Bootstrap Template</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="/public/boostrap/bootstrap.css" type="text/css">


        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="/public/css/blog-post.css" type="text/css">
        <link rel="stylesheet" href="/public/css/post.css" type="text/css">

    </head>

    <body>

    <!-- Page Content -->
    <div class="container">

        <!-- Navigation -->

        <div class="row">

            <!-- Post Content Column -->
            <div class="col-lg-8">

                <!-- Title -->
                <#if user??>
                    <div class="card my-4">
                        <h5 class="card-header">Leave a Comment:</h5>
                        <div class="card-body">
                            <form method="post" action="/add">
                                <div class="form-group">
                                    <textarea class="form-control" rows="3" name="header"></textarea>
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" rows="3" name="text"></textarea>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <button type="submit" class="btn btn-light"
                                        style="background-color: #126220; color: white ">
                                    Submit
                                </button>
                            </form>
                        </div>
                    </div>
                <#else>
                    <hr>
                    <div class="card my-4" style="align-items: center; height: 100px">
                        Only authorized users can add posts
                        <a href="/login" class="btn btn-light" style="width: 100px; margin-top: 20px">Login</a>
                    </div>
                </#if>
            </div>
        </div>
    </div>

    </body>

</#macro>
<@main/>