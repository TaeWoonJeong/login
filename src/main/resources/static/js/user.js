let index={
    init: function() {
        $("#btn-join").on("click",()=>{
            this.saveUser();
        });
    },

    saveUser:function() {
        let data={
            username:$("#username").val(),
            password:$("#pwd").val(),
            email:$("#email").val()
        };
        //console.log(data);
        //alert(data);
        $.ajax({
            type:"POST",
            url:"/auth/joinProc",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8", //요청의 타입이 뭔지
            dataType:"json" //응답 데이터의 형식이 뭔지
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            location.href="/index";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}
index.init();