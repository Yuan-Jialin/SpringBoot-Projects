($(function () {

    show();

    $.ajax({
        url:"/user/getNameById",
        type:"post",
        dataType: "json",
        headers:{'token':$.cookie("token")
        },
        data:{
            userId: $.cookie('userId')
        },
        success: function (result) {
            if (result.code==0) {
               $("#shortCut_right").children().eq(0).html(result.data);
            }
        }
    });
    //计算商品数
    function proNum() {
        var pronum = 0;
        $(".change_box").each(function () {
            var num = parseInt($(this).children().eq(1).find("#dcr_t_num").children().html());
            pronum += parseInt(num);
        });
        $("#productNum").html(parseInt(pronum));
    }

    //总商品金额
    function totalPrice() {
        var totalPrice = 0;
        $(".change_box").each(function () {
            var num = parseInt($(this).children().eq(1).find("#dcr_t_num").children().html());
            var price = parseInt($(this).children().eq(1).find("#dcr_t_mid").children().eq(0).html());
            var eachPrice = parseInt(num)*parseInt(price);
            totalPrice += parseInt(eachPrice);
        })
        $("#productPrice").html(parseInt(totalPrice));
        $("#realPay").html(parseInt(totalPrice)+8);
    }


    function show() {
            $.ajax({
                url: "http://localhost:8080/user/showItem",
                type: "post",
                traditional:true,
                dataType: "json",
                headers:{'token':$.cookie("token")
                },
                data: {
                    IdArry: $.cookie('IdArry')
                },
                success: function (result) {
                    if(result.code == 0){
                        var $node = $(".change_box").clone(true);
                        if(result.count>=2){
                            for(var i = 1; i<result.count; i++){
                                if(i > 1){
                                    //最外：checkutStep
                                    var h = $("#checkoutSteps").height();
                                    h+=190;
                                    $("#checkoutSteps").height(h);
                                    //中间：deliver
                                    h = $("#deliver").height();
                                    h += 165;
                                    $("#deliver").height(h);
                                    //中下:deliver_cont
                                    h = $("#deliver_cont").height();
                                    h+=145;
                                    $("#deliver_cont").height(h);
                                }

                                //最内:deliver_cont_right
                                h = $("#deliver_cont_right").height();
                                h+=145;
                                $("#deliver_cont_right").height(h);
                            }

                        }
                        $(".change_box").detach();
                        for(var i=0; i < result.count; i++){
                            $node.children().eq(0).html(result.data[i].businessName);
                            var imgsrc = ".." + result.data[i].productImgUrl;
                            $node.children().eq(1).find("img").attr("src",imgsrc);
                            $node.children().eq(1).find("#dcr_t_left").children("ul").children("li").eq(0).html(result.data[i].productName);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(0).html(result.data[i].productPrice);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(1).html(result.data[i].detail1);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(2).html(result.data[i].productId);
                            $node.children().eq(1).find("#dcr_t_mid").children().eq(3).html(result.data[i].cartItemId);
                            $node.children().eq(1).find("#dcr_t_num").children().html(result.data[i].num);
                            $("#deliver_cont_right").append($node);
                            $node = $(".change_box").eq(0).clone(true);
                        }
                        proNum();
                        totalPrice();
                    }else{
                        alert("请先登录！");
                        window.location.href="/user/login.html";
                    }
                },error: function () {
                    alert("请求失败！");
                }
            });
        }

        //点击显示，再点击隐藏
        $("#more_addr").click(function () {
            if($("#no_display").is(':hidden')) {
                $("#no_display").show();
            }else{
                $("#no_display").hide();
            }
        })

        //显示地址
        $.ajax({
            url:"/user/getAddress",
            type:"post",
            dataType:"json",
            // headers:{'token':$.cookie("token")
            // },
            data:{
                userId: $.cookie('userId')
            },
            success:function (result) {
                if(result.code == 0){
                    var $model = $("#selectID");
                    if (result.count == 0){
                        alert("该用户未设置地址信息！");
                    } else {
                        $("#addr_name").children().eq(0).html(result.data[0].receiveName);
                        for (var i = 0; i < result.count;i++){
                            var addressId =result.data[i].userAddressId;
                            var recName = result.data[i].receiveName;
                            var addressItem = result.data[i].address +"&nbsp;&nbsp;&nbsp;" +result.data[i].receiveTel;
                            $model.append("<option value="+addressId+" code = "+recName+">"+addressItem+"</option>");
                        }
                    }
                }else{
                    alert("请先登录！");
                    window.location.href="/user/login.html";
                }
            },error:function () {
                alert("请求失败！");
            }
        })

        //提交订单
        $("input[name='submitOrder']").click(function () {
            $.ajax({
                url:"/user/order",
                type:"post",
                dataType:"json",
                data:{
                    userId: $.cookie('userId'),
                    userAddressId: $("#selectID").find("option:selected").val(),
                    totalPrice: $("#realPay").html(),
                    IdArry: $.cookie('IdArry')
                },success:function (result) {
                    if (result.code == 0){
                        $.cookie("orderId",result.data.orderId,{path:'/user/pay.html'});
                        $.ajax({
                            url:"/user/deleteWhenCreateOrder",
                            type:"post",
                            dataType: "json",
                            data:{
                                IdArry: $.cookie('IdArry')
                            },success:function (result) {
                                if(result.code!= 0){
                                    // alert("删除购物车信息失败！");
                                }
                            },error:function () {
                                // alert("删除购物车信息的请求失败！")
                            }
                        })

                        window.location.href = "/user/pay.html";
                    } else {
                        alert("向订单插入出错！")
                    }
                },error:function () {
                    alert("向服务器请求失败！");
                }
            })
        })


        $("#selectID").change(function () {
            $("#addr_name").children().eq(0).html($("#selectID").find("option:selected").attr("code"));
        })


        playauto();//playauto()函数控制弹出框居中显示
        function playauto() {
            var _width=$(window).width();//获取浏览器窗口宽度
            var _height=$(window).height();//获取高度
            $(" #address").css({left:_width/2-300,top:_height/2-175});//使弹出框居中
        }

        $(".dl").click(function () {//点击超链接按钮显示bg、login样式
            playauto();//再次点击登陆按钮时，弹出框仍然处于居中位置
            $("#bg").show();
            $("#address").show();
        })
        $(".close").click(function () {//点击弹出框上的X按钮隐藏bg、address样式，即关闭弹出框
            $("#bg").hide();
            $("#address").hide();
        });

        $("input[name='subadd']").click(function () {
            var userId = $.cookie('userId');
            var data = $("#form1").serialize() + "&userId="+userId;
            // alert($("#form1").serialize());
            $.ajax({
                url:"/userBack/insertAddress",
                type:"post",
                dataType:"json",
                data: data,
                success:function (result) {
                    if(result.code==0){
                        alert("添加成功！");
                        window.location.reload();
                    }else {
                        alert("添加失败！");
                    }
                },error:function () {
                    alert("请求访问失败！");
                }
            })
        })
    })
)