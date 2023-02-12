(
    $(function () {
        $.cookie("productId",null,{path:"/"});

        $.ajax({
            url:"/user/getNameById",
            type:"post",
            dataType: "json",
            headers:{'token':$.cookie("token")
            },
            data:{
                userId: $.cookie('userId')
            },success:function (result) {
                if(result.code==0) {
                    $("#m_right_first").children().eq(0).html(result.data);
                    $("#m_right_first").children().eq(1).html("");
                }
            }
        })

        $.ajax({
            url : "/user/fuzzyQueryProduct",
            type: "post",
            dataType:"json",
            data:{
                name: decodeURI(decodeURI($.cookie("name")))
            },
            success:function (result) {
                if(result.code==0){
                    var $modle = $(".product").clone(true);
                    $(".product").eq(0).detach();
                    for(var i = 0; i <result.count; i++){
                        var $node = $modle.children().children();
                        var url = ".."+ result.data[i].productImgUrl;
                        $node.eq(0).children().eq(0).attr("src",url);
                        $node.eq(1).children().eq(1).html(result.data[i].productPrice);
                        $node.eq(2).children().eq(0).html(result.data[i].productName);
                        $node.eq(2).children().eq(2).html(result.data[i].commentCount + "条评论");
                        $node.eq(2).children().eq(4).html(result.data[i].businessName);
                        $node.eq(2).children().eq(5).html(result.data[i].productId);
                        $(".product-ul").append($modle);
                        $modle = $(".product").eq(0).clone(true);
                    }
                    //删除hover样式
                    $(".p-item").css("pointer-events","none");
                }else {
                    alert("服务器查询异常");
                }
            },error: function () {
                alert("出现异常");
            }
        })
        // $.cookie("name",null,{path:'/'});

        $(".proitemlist").click(function () {
            var pro= $(this).children().eq(2).children().eq(5).html();
            $.cookie("productId",pro,{path:"/"});
            window.location.href="/user/details.html";
        })
    })
)