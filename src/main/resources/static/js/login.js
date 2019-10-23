

var $btn = $('#btn');
console.log($btn);

$btn.on('click', function () {
    $.ajax({
        url : "/login",
        type : "post",
        dataType : "json",
        success: function (data) {
            console.log(data);
            var json = data;
            console.log(json.message1)
            console.log(data.message1);
        },
        error: function () {
            alert("fail")
        }
    })
})