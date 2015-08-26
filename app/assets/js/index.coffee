$ ->
$.get "/urls", (urls) ->
    $.each urls, (index, url) ->
     $('#print').append $('<li>').text url.url