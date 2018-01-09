
function getData() {
    var data;
    $('#images').on("paste", function(e) {
        $.each(e.originalEvent.clipboardData.items, function() {
            this.getAsString(function(str) {
                data = str;
            });
        });
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
                data.clear;
                console.log(data);

            }
        });
    });


}

function main() {
    getData();
}


$(document).ready(main);