$ ->
$.get "/notification/messages", (messages) ->
    $.each messages, (notification, message) ->
     $('#print').append $('<li>').text message.msg