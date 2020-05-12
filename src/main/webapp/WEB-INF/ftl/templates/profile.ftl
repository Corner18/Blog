<#ftl encoding="UTF-8"/>
<#include "base.ftl"/>
<#macro content>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/public/css/profile.css" type="text/css">
    <meta charset="UTF-8">
    <body>
    <style>
        .error {
            color: #ff0000;
        }
    </style>


    <div class="container">
        <div class="row">
            <div class="col-md-6 img">
                <#if user.avatar??>
                    <img src="${user.avatar}" width="300" height="150"
                         alt="" class="img-rounded">
                <#else>
                    <img class="d-flex mr-3 rounded-circle"
                         src="https://miro.medium.com/max/720/1*W35QUSvGpcLuxPo3SRTH4w.png" alt=""
                         width="50" height="50">
                </#if>

            </div>
            <div class="col-md-6 details">
                <blockquote>
                    <#if user??>
                        <h5>${user.name}</h5>
                        <h5>${user.email}</h5>
                        <h5>Do you want upload photo?
                            <a href="/storage">Let's do it</a>
                        </h5>
                        <div class="row second">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <a href="/favourites" class="btn btn-primary">Favourite posts</a>
                        </div>
                    </#if>


                </blockquote>
            </div>
        </div>
    </div>

    </body>
</#macro>
<@main/>