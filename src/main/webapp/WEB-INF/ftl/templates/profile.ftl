<#ftl encoding="UTF-8"/>
<#include "base.ftl"/>
<#import "spring.ftl" as spring />
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

    <style>
        ul.hr {
            margin: 0; /* Обнуляем значение отступов */
            padding: 4px; /* Значение полей */
        }

        ul.hr li {
            display: inline; /* Отображать как строчный элемент */
            margin-right: 5px; /* Отступ слева */
            padding: 3px; /* Поля вокруг текста */
        }
    </style>


    <div class="container">
        <div class="row">
            <div class="col-md-6 img">
                <#if user.avatar??>
                    <img src="${user.avatar}" width="300" height="150"
                         alt="" class="img-rounded">
                </#if>

            </div>

            <#if user??>
                <div class="col-md-6 details">
                    <blockquote>
                        <h5>${user.name}</h5>
                        <h5>${user.email}</h5>
                        <h5><@spring.message 'profile.page.photo'/></h5>
                        <h5><a href="/storage"><@spring.message 'profile.page.doit'/></a></h5>

                        <div class="row second">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <a href="/favourites" class="btn btn-primary"><@spring.message 'profile.page.favs'/></a>
                        </div>
                    </blockquote>
                </div>


            </#if>

        </div>


    </div>
    </body>
</#macro>
<@main/>