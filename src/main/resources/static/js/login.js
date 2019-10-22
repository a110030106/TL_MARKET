

var $btn = $('#btn');
console.log($btn);

$btn.on('click', function () {
    $.ajax({
        url : "/login",
        type : "post",
        success: function (data) {
            console.log(data);
            var json = data;
            console.log(json.errormessage)
            console.log(data.errormessage);
        },
        error: function () {
            alert("fail")
        }
    })
})