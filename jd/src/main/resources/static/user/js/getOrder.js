($(function () {

    $("input[name='deleteOrder']").click(function () {
        var orderid = $(this).parent().parent().parent().prev().children().eq(1).html();
        $.ajax({
            url:"/user/deleteOrder",
            type:"post",
            dataType:"json",
            data:{
                orderId: orderid
            },success:function (result) {
                if (result.code==0){
                    alert("删除成功！");
                    window.location.reload();
                } else{
                    alert(result.msg);
                }
            },error:function () {
                alert("请求响应失效！");
            }
        })
    })

    //展示订单信息
    $.ajax({
        url:"http://localhost:8080/user/getAllOrder",
        type:"post",
        dataType:"json",
        headers:{'token':$.cookie("token")
        },
        data:{
            userId:$.cookie('userId')
        },
        success:function (result){
            if(result.code == 0){
                if (result.count==0){
                    alert("该用户没有订单信息！");
                } else{
                    var $model = $(".orderBox").eq(0).clone(true);
                    var n = 0;
                    $(".orderBox").eq(0).detach();
                    for (var i = 0; i < result.count; i++){
                        if(result.data.orderId == -1){
                            n++;
                        }else {
                            $model.children().eq(0).children().eq(0).html(result.data[i].createTime);
                            $model.children().eq(0).children().eq(1).html(result.data[i].orderId);
                            var imgsrc = ".." + result.data[i].productImgUrl;
                            $model.children().eq(1).children().eq(0).find("img").attr("src", imgsrc);
                            $model.children().eq(1).children().eq(0).children().eq(0).children().eq(1).children().eq(0).html(result.data[i].productName);
                            $model.children().eq(1).children().eq(0).children().eq(0).children().eq(2).children().eq(0).html(result.data[i].num);
                            $model.children().eq(1).children().eq(1).find("span").html(result.data[i].receiveName);
                            $model.children().eq(1).children().eq(2).find("span").html(result.data[i].totalPrice);
                            $model.children().eq(1).children().eq(3).children().eq(0).html(result.data[i].orderStatus);
                            $("#main_body").append($model);
                            $model = $(".orderBox").eq(0).clone(true);
                            // for (var j = i+1; j <result.count; j++){
                            //     var $node = $(".orderitem").eq(0).clone(true);
                            //     if(result.data[j].orderId == result.data[i].orderId){
                            //         imgsrc = ".." + result.data[j].productImgUrl;
                            //         $node.children().eq(0).attr("src",imgsrc);
                            //         $node.children().eq(1).children().eq(0).html(result.data[j].productName);
                            //         $node.children().eq(1).children().eq(0).html(result.data[j].num);
                            //         result.data[j].orderId = -1;
                            //         $(".part1").eq(i-n).append($node);
                            //         $node = $(".orderitem").eq(0).clone(true);
                            //     }
                            // }
                        }
                    }

                }
            }else {
                alert("请先登录！");
                window.location.href="/user/login.html";
            }
        },error:function () {
            alert("请求响应失效！");
        }
    })

    $.ajax({
        url:"/user/getNameById",
        type:"post",
        dataType: "json",
        headers:{'token':$.cookie("token")
        },
        data:{
            userId: $.cookie('userId')
        },success:function (result) {
            $("#m_right_first").children().eq(0).children().eq(0).html(result.data);
            $("#m_right_first").children().eq(1).html("");
        }
    })

}))