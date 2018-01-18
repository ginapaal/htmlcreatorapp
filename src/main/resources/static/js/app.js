
function getData() {
    var data;
    $('#images').on("paste", function(e) {
        $.each(e.originalEvent.clipboardData.items, function() {
            this.getAsString(function(str) {
                data = str;
            });
        });
        $('#images').value = "Képek betöltése folyamatban, nyomd meg a submit gombot a módosításhoz"
    });

    $('#button').on('click', function() {
        console.log($('#title').val());
        $.ajax({
            method: 'POST',
            url: '/index',
            data: {
                images: data,
                title: $('#title').val(),
                description: $('#description').val(),
                price: $('#price').val()
            },
            success: function() {
                console.log(data);
                getId();
            }
        });
    });
}

function getId() {
    $.getJSON("/ajanlat", function(resp) {
        console.log(resp);
        $.each(resp, function(key, value) {
            window.location.href="/ajanlat/" + value;
        });
    });
}

function main() {
    getData();
}


$(document).ready(main);