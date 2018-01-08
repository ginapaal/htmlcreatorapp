
function getData() {
    var data;
    $('#images').on("paste", function(e) {
        $.each(e.originalEvent.clipboardData.items, function() {
            this.getAsString(function(str) {
                data = str;
            });
        });
    });

    $('#gomb').on('click', function() {
        $.ajax({
            method: 'POST',
            url: '/',
            data: {
                images: data
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