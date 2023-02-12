($(function () {
    console.log($.cookie("userId"));
    //获取购物车中商品的数量
    $.ajax({
        url:"/user/getCartNum",
        type:"post",
        dataType: "json",
        data:{
            userId: $.cookie('userId'),
        },
        success: function (result) {
            if(result.code == 0){
                $("#search_top_center").children().eq(1).html(result.data);
            }
        }
    })


    $.ajax({
        url:"/testGetUserId",
        type:"post",
        headers:{'token':$.cookie("token")
        },
        dataType: "json",
        success: function (result) {
            if(result.code==0) {
                $.cookie("userId", result.data, {path: '/'});
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
                            $("#m_right_first").find("span").html(result.data);
                            $("#m_right_first").children().eq(1).html("");
                            $("#user_show").html("你好，"+result.data+"<br>欢迎逛京东");
                        }
                    }
                })
            }
        }
    })

    $.cookie("name",null,{path:'/'});
    $(".button").click(function () {
        var keyword = $("input[name='keyword']").val();
        if (keyword==null||keyword==''){
            alert("搜索信息不能为空");
        }else {
            $.ajax({
                url: "/user/fuzzyQueryProduct",
                type: "post",
                datatype: "json",
                data: {
                    name:$("#keyword").val()
                },
                success: function (result) {
                    if (result.code==0) {
                        $.cookie("name",encodeURI(encodeURI($("#keyword").val())),{path:'/user/productList.html'});
                        window.location.href="http://localhost:8080/user/productList.html"
                    }else{
                        alert("error");
                    }
                }

            })
        }
    })


        //轮播图
        var index = 0;//用index记录下标,默认为0
        var imgs = $('#fs_col2 li');
        //1.右边箭头点击事件
        $('.scroll_right').click(right);
        //右箭头点击事件处理函数
        function right() {
            index++;
            //如果当前是最后一张,此时index=lis.length-1,index++后index=lis.length
            // 此时应让index=0
            if (index == imgs.length) {
                index = 0;
            }
            //显示对应下标的图片,让其他兄弟隐藏
            imgs.eq(index).fadeIn().siblings().fadeOut();
            //给对应下标的小圆点按钮添加current类名,其他兄弟移除current类名
            $('.dot i').eq(index).addClass('current').siblings().removeClass('current');
        }
        //2.左边箭头点击事件
        $('.scroll_left').click(function () {
            index--;
            //如果当前是第一张(index=0),index--后,index<0,此时应显示最后一张,让index=lis.length-1
            if (index < 0) {
                index = imgs.length - 1;
            }
            //显示对应下标的图片,让其他兄弟隐藏
            imgs.eq(index).fadeIn().siblings().fadeOut();
            //给对应下标的小圆点按钮添加current类名,其他兄弟移除current类名
            $('.dot i').eq(index).addClass('current').siblings().removeClass('current');
        })

        //3.底部圆点按钮鼠标移入事件
        btns = $('.dot i');
        for (var i = 0; i < btns.length; i++) {
            //设置index属性,记录当前下标
            btns.eq(i).attr('index', i);
            //给btn注册鼠标移入事件
            btns.eq(i).mouseenter(fn);
        }
        //btn的事件处理函数
        function fn() {
            var index = $(this).attr('index');
            imgs.eq(index).fadeIn().siblings().fadeOut();
            $(this).addClass('current').siblings().removeClass('current');
        }

        //4.自动轮播
        //先执行一次定时器
        var timeid = setInterval(function () {
            right()
        }, 3000)
        //鼠标移出div时,设置定时器,调用右边箭头的点击事件
        $('#fs_col2').mouseleave(function () {
            timeid = setInterval(function () {
                right()
            }, 3000)
        })
        //鼠标移入div时,清除定时器
        $('#fs_col2').mouseenter(function(){
            clearInterval(timeid)
        })




    }))