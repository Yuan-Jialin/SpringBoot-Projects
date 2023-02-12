package com.lanqiao.jd.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lanqiao.jd.annotations.UserLoginToken;
import com.lanqiao.jd.dao.CartItemMapper;
import com.lanqiao.jd.entity.*;
import com.lanqiao.jd.service.*;
import com.lanqiao.jd.entity.*;
import com.lanqiao.jd.service.*;
import com.lanqiao.jd.util.CodeUtil;
import com.lanqiao.jd.util.Result;
import com.lanqiao.jd.util.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.lanqiao.jd.util.CodeUtil.getNewcode;
import static com.lanqiao.jd.util.CodeUtil.setNewcode;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    TokenService tokenService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    ProductService productService;
    @Autowired
    SmsUtils smsUtils;
    @Autowired
    CodeUtil codeUtil;
    @Autowired
    OrderService orderService;
    @Autowired
    UserAddressService userAddressService;
    @Autowired
    CartItemMapper cartItemMapper;


    //注册验证手机号是否存在
    @PostMapping("/verifyPhoneNumber")
    public Result verifyPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber){
        return userService.verifyPhoneNumber(phoneNumber);
    }


    //注册功能->向数据库中插入一条user记录
    //need:userName password  phoneNumber
    @PostMapping("/register")
    public Result register(User user) {
        return userService.register(user);
    }

    //    @PostMapping("/login")
//    public Result login(User user){
//        return userService.login(user);
//    }


    //登录 -> 检查用户名密码与数据库中的记录是否匹配
    @PostMapping("/login")
    public Result login(User user) {


        User userForBase = userService.findByUsername(user);

        if (userForBase == null) {
            return Result.createByFailure("登录失败,用户不存在");
        } else {
//            if (!userForBase.getPassword().equals(user.getPassword())) {
            if (!(tokenService.getMd5(user.getPassword()).equals(userForBase.getPassword()))){
                return Result.createByFailure("登录失败,密码错误");
            } else {
                String token = tokenService.getToken(userForBase);
                List<Object> listObject = new ArrayList<>();
                listObject.add(userForBase);
                listObject.add("token:"+token);
                return Result.createSuccessResult(2,listObject);
            }
        }
    }

    @UserLoginToken
    @PostMapping("/getMessage")
    public String getMessage(){
        try {
            return "你已通过验证";
        }catch (Exception e){
            return "请登录";
        }
    }

    //评论相关
    @UserLoginToken
    @PostMapping("/insertComment")
    public Result insertComment(Comment comment){return commentService.insertComment(comment);}


    //展示某个商品的评论
    @PostMapping("/showCommentByProductId")
    public Result showCommentByItemId(@RequestParam(name = "productId") int productId){
        return commentService.showCommentByItemID(productId);
    }


    //主页->搜索栏：根据商品名称模糊查询
    //need:name
    @PostMapping("/fuzzyQueryProduct")
    public Result fuzzyQueryProduct(String name){
        return productService.fuzzyQueryProduct(name);
    }

    //购物车相关
    //插入一条信息 参数：userId ，cartItem
    @UserLoginToken
    @PostMapping("/insertCartItem")
    public Result insertCartItem(@RequestParam(name = "userId") int userId, CartItem cartItem){
        return cartItemService.insertCartItem(userId,cartItem);
    }

    //删除信息 参数：userId，producId
    @PostMapping("/deleteCartItem")
    public  Result deleteCartItem(@RequestParam(name = "userId") int userId,@RequestParam(name = "productId") int productId){
        return cartItemService.deleteCartItem(userId,productId);
    }

    //在生成订单时删除购物车相关信息
    @PostMapping("/deleteWhenCreateOrder")
    public Result deleteWhenCreateOrder(@RequestParam(name = "IdArry" )String IdArry){
        String[] split = IdArry.split(",");
        int []test = new int[split.length];
        for (int i = 0; i < split.length; i++){
            test[i] = Integer.parseInt(split[i]);
        }
        return cartItemService.deleteWhenCreateOrder(test);
    }

    //查看购物车信息
    @UserLoginToken
    @PostMapping("/showCartItem")
    public Result showCartItem(@RequestParam(name = "userId") int userId){
        System.out.println("showCartItem"+userId);
        return cartItemService.showCartItem(userId);
    }
    //数量+1
    @PostMapping("/addCartItemNum")
    public Result addCartItemNum(@RequestParam(name = "userId") int userId,@RequestParam(name = "productId") int productId){
        return cartItemService.addCartItemNum(userId,productId);
    }

    //数量-1
    @PostMapping("/subCartItemNum")
    public Result subCartItemNum(@RequestParam(name = "userId") int userId,@RequestParam(name = "productId") int productId){
        return cartItemService.subCartItemNum(userId,productId);
    }

    //购物车到订单过度页面展示
    @UserLoginToken
    @PostMapping("showItem")
    public Result showItem(@RequestParam(name = "IdArry" )String IdArry){
         String[] split = IdArry.split(",");
        int []test = new int[split.length];
        for (int i = 0; i < split.length; i++){
            test[i] = Integer.parseInt(split[i]);
        }
        return orderService.showItem(test);
    }


    //发送手机验证码
    @PostMapping("/sendMsg")
    //need:phoneNumber
    public Result sendMsg(@RequestParam(name = "phoneNumber") String phoneNumber){
        setNewcode();
        String code = Integer.toString(getNewcode());
        try{
            SendSmsResponse sendSms =smsUtils.sendSms(phoneNumber,code);//填写你需要测试的手机号码
            System.out.println("短信接口返回的数据----------------");
            System.out.println("Code=" + sendSms.getCode());
            System.out.println("Message=" + sendSms.getMessage());
            System.out.println("RequestId=" + sendSms.getRequestId());
            System.out.println("BizId=" + sendSms.getBizId());
            return Result.createSuccessResult();
        }catch(Exception e){
            return Result.createByFailure();
        }
    }

    //商品详情页
    @PostMapping("/detailPage")
    public Result detailPage(int userId, int productId){
        return productService.productItem(userId,productId);
    }


    //提交订单(先向订单表中插入订单信息，获取到orderId，然后下向)
    //need:userId userAddressId totalPrice
    //need:OrderItem(productId, num)
    @PostMapping("/order")
    public Result insertOrder(@RequestParam(name = "userId") int userId,@RequestParam(name = "userAddressId") int userAddressId,@RequestParam(name = "totalPrice") int totalPrice,@RequestParam(name = "IdArry" )String IdArry){
//        OrderVo orderVo
//        for(OrderItem orderItem:orderVo.getOrderItem()){
//            System.out.println(orderItem.toString());
//        }
//        System.out.println(orderVo.getUserId() + " " + orderVo.getUserAddressId() + " " + orderVo.getTotalPrice() + " ");
//        return Result.createSuccessResult();

        OrderVo orderVo = new OrderVo();
        List<OrderItem> list = new ArrayList<>();
        CartItem cartItem;
        String[] split = IdArry.split(",");
        for (int i = 0; i < split.length; i++){
            cartItem = cartItemMapper.selectByPrimaryKey(Integer.parseInt(split[i]));
            OrderItem orderItem = new OrderItem();
            orderItem.setNum(cartItem.getNum());
            orderItem.setProductId(cartItem.getProductId());
            list.add(orderItem);
        }
        orderVo.setOrderItem(list);
        orderVo.setUserId(userId);
        orderVo.setUserAddressId(userAddressId);
        BigDecimal a = new BigDecimal(totalPrice);
        orderVo.setTotalPrice(a);
        return orderService.insertOrder(orderVo);
    }

    //得到订单 orderId
    @PostMapping("/getOrderPrice")
    public Result getOrderPrice(@RequestParam(name = "orderId") int orderId){
        return orderService.getOrderByOrderId(orderId);
    }


    //订单付款
    @PostMapping("/pay")
    //need:userId  orderId
    public Result pay(Order order){
       return orderService.pay(order);
    }

    //余额充值
    @PostMapping("/addBalance")
    //need:balance userId password
    public Result addBalance(User user){
        return userService.addBalance(user);
    }


    @UserLoginToken
    @PostMapping("/getNameById")
    public Result<String> getNameById(@RequestParam(name = "userId")int userId){
        User user =  userService.findUserById(userId);
        return Result.createSuccessResult(user.getUserName());
    }

    //查询一个用户的所有的订单信息
    @UserLoginToken
    @PostMapping("/getAllOrder")
    public Result getAllOrderByUserId(int userId){
        return orderService.getAllOrderByUserId(userId);
    }


    //订单删除
    @PostMapping("/deleteOrder")
    public Result deleteOrder(@RequestParam(name = "orderId") int orderId){
        return orderService.deleteOrder(orderId);
    }

    //查询地址
    @PostMapping("/getAddress")
    public Result getAddress(@RequestParam(name = "userId") int userId){
        return userAddressService.getAddress(userId);
    }

    @PostMapping("/getCartNum")
    public Result getCartNum(int userId){
        return cartItemService.getCartNum(userId);
    }
}
