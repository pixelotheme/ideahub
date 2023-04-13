var main ={

    init : function(){
        var _this = this;
        $('#btn-create').on('click', function(){
            alert("클릭")
            _this.save();
        });
    },
    save : function(){
        var data = {
            title : $('#title').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'POST',
            url : "/api/ideaboard/post",
            dataType : "json",
            contentType : "application/json; charset=utf-8",
            data : JSON.stringify(data)

        }).done(function(){
            alert("등록성공");
            location='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })

    }
}