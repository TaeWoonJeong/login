let index={
    init: function() {
        $("#btn-join").on("click",()=>{
            this.saveUser();
        });
        $("#btn-login").on("click",()=>{
            this.login();
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
            url:"/user/api/join",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8", //요청의 타입이 뭔지
            dataType:"json" //응답 데이터의 형식이 뭔지
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            location.href="/blog/index";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    login:function() {
        let data={
            username:$("#username").val(),
            password:$("#pwd").val()
        };
        //console.log(data);
        //alert(data);
        $.ajax({
            type:"POST",
            url:"/user/api/login",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8", //요청의 타입이 뭔지
            dataType:"json" //응답 데이터의 형식이 뭔지
        }).done(function(resp){
            alert("로그인이 완료되었습니다.");
            location.href="/blog/index";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
}
index.init();