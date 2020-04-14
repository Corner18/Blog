<#ftl encoding='UTF-8'>
<#include "base.ftl"/>
<#macro content>
    <head>
        <script
                src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
                crossorigin="anonymous"></script>
        <script>

            function sendMessage(text, sender, receiver,pageId) {
                let body = {
                    'text': text,
                    'sender': sender,
                    'receiver': receiver,
                    'pageId' : pageId
                };

                $.ajax({
                    url: "/messages",
                    method: "POST",
                    data: JSON.stringify(body),
                    contentType: "application/json",
                    dataType: "json",
                    complete: function () {
                        if (text === 'Login') {
                            receiveMessage(sender, receiver, pageId)
                        }
                    }
                });
            }

            // LONG POLLING
            function receiveMessage(sender, receiver, pageId) {
                $.ajax({
                    url: "/messages?pageId=" + pageId,
                    method: "GET",
                    dataType: "json",
                    contentType: "application/json",
                    success: function (response) {
                        $('#messages').first().before("<strong>" + response[0]['sender'] + ": </strong>" + response[0]['text'] + "<br>");
                        receiveMessage(sender, receiver, pageId);
                    }
                })
            }

        </script>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Chat</title>
        <link rel="stylesheet" href="/public/boostrap/bootstrap.css">
        <link rel="stylesheet"
              href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.1.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/posts.css" type="text/css">
    </head>

    <body onload="sendMessage('Login', '${sender}' , '${receiver}' , '${pageId}' )">
    <div>
        <input id="message" placeholder="Your message">
        <button onclick="sendMessage(
                $('#message').val(), '${sender}' , '${receiver}' , '${pageId}' )">Send
        </button>
    </div>
    <div>
        <#list messages as message>
            <strong> ${message.sender}: </strong> ${message.text} <br>
        </#list>
        <div id="messages">

        </div>
    </div>
    </body>
</#macro>
<@main/>