(     $(function() {
        //$.cookie("productId", null, {path: '/'});
        var $yin = $(".yin");
        $(".Ul li img").mouseover(function () {
            $(this).parent().addClass("li").siblings().removeClass("li");
            $("#pian").attr("src", $(this).attr("src"));
            $("#zhao").attr("src", $(this).attr("src"));
        }).mouseout(function () {
            $(this).parent().removeClass("li");
        });
        var l = $(".shang").eq(0).offset().left;
        var t = $(".shang").eq(0).offset().top;
        var width1 = $(".yin").outerWidth() / 2;
        var height1 = $(".yin").outerHeight() / 2;
        var maxL = $(".shang").width() - $yin.outerWidth();
        var maxT = $(".shang").height() - $yin.outerHeight();
        var bili = $("#zhao").width() / $("#pian").width();
        $(".shang").mousemove(function (e) {
            var maskL = e.clientX - l - width1,
                maskT = e.clientY - t - height1;
            if (maskL < 0) {
                maskL = 0
            }
            ;
            if (maskT < 0) {
                maskT = 0
            }
            ;
            if (maskL > maxL) {
                maskL = maxL
            }
            ;
            if (maskT > maxT) {
                maskT = maxT
            }
            ;
            $yin.css({
                "left": maskL,
                "top": maskT
            });
            $(".xia").show();
            $(".yin").show();
            $("#zhao").css({
                "margin-left": -maskL * bili,
                "margin-top": -maskT * bili
            });
        });
        $(".shang").mouseleave(function () {
            $(".xia").hide();
            $(".yin").hide();
        });
        var marginLeft = 0;
        $(".you").click(function () {
            marginLeft = marginLeft - 64;
            if (marginLeft < -64) {
                marginLeft = -64
            }
            ;
            $(".tab ul").stop().animate({
                    "margin-left": marginLeft
                },
                "fast");
        });
        $(".zuo").click(function () {
            marginLeft = marginLeft + 64;
            if (marginLeft > 0) {
                marginLeft = 0
            };
            $(".tab ul").stop().animate({
                    "margin-left": marginLeft
                },
                "fast");
        });
        $(".lie li").click(function () {
            var index = $(this).index();
            $(this).addClass("ll").siblings().removeClass("ll");
            $(".bao1>div").eq(index).show().siblings().hide();
        });
        $("input[value='+']").click(function () {
            $(this).parent().prev().val(parseInt($(this).parent().prev().val()) + 1);
            $("input[name='num']").trigger("input");
        });
        $("input[value='-']").click(function () {
            var $num = parseInt($(this).parent().prev().val());
            if ($num > 1) {
                $(this).parent().prev().val($num - 1);
                $("input[name='num']").trigger("input");
            } else {
                $(this).closest("ul").find("span").click();
            }
        });

        //发表评论
        $("input[type='submit']").click(function () {
            $.ajax({
                url: "/user/insertComment",
                type: "post",
                headers: {
                    'token': $.cookie("token")
                },
                data: {
                    commentDetail: $("textarea[name='message']").val(),
                    commentLevel: $("input[name='level']").val(),
                    productId: $.cookie('productId'),
                    userId: $.cookie('userId')

                },
                datatype: "json",
                success: function (result) {
                    if (result.code == 0) {
                        alert("插入成功");
                    } else {
                        alert("请先登录！");
                        window.location.href = "/user/login.html";
                    }
                }
            })
        })

        //获取评论
        $.ajax({
            url:"/user/showCommentByProductId",
            type:"post",
            datatype:"json",
            scriptCharset:"utf-8",
            data:{productId:$.cookie('productId')},
            success:function (result) {
                for(var i=0;i<result.data.length;i++){
                    var $node = $("div[class='article']:eq(0)").clone(true);
                    $node.children().eq(0).text("用户："+result.data[i].userName);
                    //星级
                    var star = "";
                    for(var j = 0; j <result.data[i].commentLevel ;j++){
                        star+="*"
                    }
                    $node.children().eq(1).text(star);
                    // $node.children().eq(1).text(result.data[i].commentLevel);
                    $node.children().eq(2).text(result.data[i].commentDetail);
                    $node.children().eq(3).text(result.data[i].commentTime);
                    $("div[class='down']").prepend($node);
                }
                var d = parseInt(result.data.length);
                $(".down").children().eq(d).remove();
            }
        })
        //获取详情页需要的数据
        $.ajax({
            url:"/user/detailPage",
            type:"post",
            datatype:"json",
            scriptCharset:"utf-8",
            data:{
                userId:$.cookie('userId'),
                productId:$.cookie('productId')
            },
            success:function (result) {

                console.log(result);
                $("div[class='name']").text(result.data[0].productName);

                $("div[class='test']").text(result.data[0].productItemId);
                $(".price span").eq(1).text(result.data[0].productPrice);
                $("div[class='business']").text("由"+result.data[0].businessName+"配送");
                $("#one").src="/common/download"+result.data[0].detail1;
                $(".lie li").eq(1).text("评论");
                document.getElementById('one').src="/common/download?name="+result.data[0].detail1;
                $("div[class='address']").append(result.data[2][0].address);
                var up =$(".up ul li");
                $(up[0]).text("/common/download?name="+result.data[0].detail1);
                $(up[1]).text(result.data[0].detail2);
                $(up[2]).text(result.data[0].detail3);
                $(up[3]).text(result.data[0].detail4);
                $(up[4]).text(result.data[0].detail5);
                $(up[5]).text(result.data[0].detail6);
                $(up[6]).text("商家:"+result.data[0].businessName);
                // $("div[class='up']").html(result.data[0].detail1+ "<br/>");
                // $("div[class='up']").append(result.data[0].detail2+ "<br/>");
                // $("div[class='up']").append(result.data[0].detail3+ "<br/>");
                // $("div[class='up']").append(result.data[0].detail4+ "<br/>");
                // $("div[class='up']").append(result.data[0].detail5+ "<br/>");
                // $("div[class='up']").append("商家:"+result.data[0].businessName);
                var proitemid = result.data[0].productItemId;
                $.cookie("productItemId",proitemid,{path:'/user/details.html'});
                //下面是图片路径
                $img = result.data[1][0];
                $("#pian").attr("src", $img);
                var listImg=$(".Ul li img");
                for(var i=0;i<listImg.length;i++)
                {
                    $(listImg[i]).attr("src",result.data[1][i]);
                }
                $("#zhao").attr("src", result.data[1][i]);
            }
        })
        $("button[name='shop']").click(function () {
            $.ajax({
                url: "/user/insertCartItem",
                type: "post",
                datatype: "json",
                scriptCharset: "utf-8",
                headers:{'token':$.cookie("token")
                },
                data: {
                    userId: $.cookie('userId'),
                    productId:$.cookie('productId'),
                    num: $("input[name='num']").val()
                },
                success: function (result) {
                    window.location.href="http://localhost:8080/user/cart.html";
                }
            })
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
                if(result.code==0) {
                    $("#m_right_first").html(result.data);
                    $("#m_right_first").children().eq(0).html("");
                }
            }
        })

    })
)