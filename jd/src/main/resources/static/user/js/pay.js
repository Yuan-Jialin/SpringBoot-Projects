($(function () {
    //支付
    $("#payButton").click(function () {
        $.ajax({
            url:"http://localhost:8080/user/pay",
            type:"post",
            dataType:"json",
            data:{
                userId: $.cookie('userId'),//TODO
                orderId:$.cookie('orderId') //TODO
            },
            success:function (result){
                //余额不够
                if(result.code == -100)
                {
                    var msg = result.msg;

                    if(msg == "您已支付，请勿重复支付"){
                        alert(msg + ",即将跳转到主页");
                        window.location.href = "http://localhost:8080/user/order.html"
                    }
                    else if(msg == "余额不足，请充值")
                    {
                        $("#balanceBox").css("display","block");
                    }
                }
                else if (result.code == 0){
                    // alert("支付成功,即将跳转到主页");
                    $(".w").hide();
                    $("#success").hide();
                    $("#payButton").hide();
                    $("#paySuccess").show();
                    // window.location.href = "http://localhost:8080/user/index.html"
                }
            }
        })
    })

    //充值
    $("#addMoney").click(function () {
        $.ajax({
            url:"http://localhost:8080/user/addBalance",
            type:"post",
            dataType:"json",
            data:{
                balance:$("#money").val(),
                userId: $.cookie('userId'), //TODO
                password:$("#pwd").val()
            },
            success:function (result){
                if(result.code == 0){
                    $("#balanceBox").hide();
                    $("#success").show();
                }
                else if(result.code == -100){

                }
                else{
                    alert("充值失败，稍后重试");
                }
            }
        })
    })

    //获取用户名
    var name;
    $.ajax({
        url:"http://localhost:8080/user/getNameById",
        type:"post",
        dataType:"json",headers:{'token':$.cookie("token")
        },
        data:{
            userId:$.cookie('userId'), //TODO
        },
        success:function (result) {
            if (result.code==0) {
                name = result.data;
                //更改用户名
                $("#name").html(name);
            }else {
                alert("请先登录!");
                window.location.href="/user/login.html";
            }
        }
    })


    //更改支付金额
    $.ajax({
        url:"/user/getOrderPrice",
        type:"post",
        dataType:"json",
        data:{
            orderId: $.cookie('orderId')
        },success:function (result) {
            if(result.code == 0){
                $("#payMoney").html(result.data.totalPrice);
                $(".o-title").children().eq(0).html(result.data.orderId);
            }else {
                alert("查询订单异常！");
            }
        },error:function () {
            alert("请求服务器响应失效");
        }
    })




}))