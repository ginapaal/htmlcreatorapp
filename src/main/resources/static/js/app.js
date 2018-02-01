function getData() {
    var data;
    $('#images').on("paste", function (e) {
        $.each(e.originalEvent.clipboardData.items, function () {
            this.getAsString(function (str) {
                data = str;
            });
        });
    });

    $('#button').on('click', function () {
        console.log($('#title').val());
        alert(data);
        $.ajax({
            method: 'POST',
            url: '/index',
            data: {
                images: data,
                title: $('#title').val(),
                description: $('#description').val(),
                price: $('#price').val()
            },
            success: function () {
                console.log(data);
                getId();
            }
        });
    });
}

function refreshPage() {
    var deleteButton = $('.delete_btn');
    deleteButton.on('click', function() {
        setTimeout(location.reload.bind(location), 100);
    });
}

function getId() {
    $.getJSON("/ajanlat", function (resp) {
        console.log(resp);
        $.each(resp, function (key, value) {
            window.location.href = value + "/edit";
        });
    });
}

function getCardId() {
    var btn = $('.delete_btn');
    btn.on('click', function () {
        var btnId = this.id;
        var url = window.location.href;
        var urlelemList = url.split("/");
        var htmlId = urlelemList[3];
        console.log(htmlId);
        $.ajax({
            method: 'POST',
            url: "/" + htmlId + "/edit/delete",
            data: {
                buttonId: btnId
            },
            success: function () {
                console.log("everything's alright");
            }
        });
    })
}

function main() {
    getData();
    getCardId();
    refreshPage();
}


$(document).ready(main);