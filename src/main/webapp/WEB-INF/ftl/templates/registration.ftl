<#ftl encoding="UTF-8"/>
<#include "base.ftl"/>
<#import "spring.ftl" as spring />
<#macro content>

    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <title><@spring.message 'registration.page.registration'/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"
          type="text/css">
    <link rel="stylesheet" href="/public/css/auth.css" type="text/css">
    </head>
    <body>

    <main class="my-form">
        <div class="cotainer">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <<div class="card-header"><@spring.message 'registration.page.registration'/></div>
                        <div class="card-body">
                            <@spring.bind "r"/>
                            <form name="my-form" action="/registration" method="post">
                                <div class="form-group row">
                                    <label for="email" class="col-md-4 col-form-label text-md-right"><@spring.message 'registration.page.email'/></label>
                                    <div class="col-md-6">
                                        <@spring.formInput "r.email"/>
                                        <@spring.showErrors "<br>","error"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="name"
                                           class="col-md-4 col-form-label text-md-right"><@spring.message 'registration.page.name'/></label>
                                    <div class="col-md-6">
                                        <@spring.formInput "r.name"/>
                                        <@spring.showErrors "<br>","error"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="phone"
                                           class="col-md-4 col-form-label text-md-right"><@spring.message 'registration.page.phone'/></label>
                                    <div class="col-md-6">
                                        <@spring.formInput "r.phoneNumber"/>
                                        <@spring.showErrors "<br>","error"/>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="password" class="col-md-4 col-form-label text-md-right"><@spring.message 'registration.page.password'/></label>
                                    <div class="col-md-6">
                                        <@spring.formPasswordInput "r.password"/>
                                        <@spring.showErrors "<br>","error"/>
                                    </div>
                                </div>

                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                                <div class="col-md-6 offset-md-4">
                                    <button class="btn btn-info btn-block my-4" type="submit"><@spring.message 'registration.page.join'/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>

    </main>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>
<@main/>