sql代码
```sql
create table if not exists tb_user
(
    user_id      int primary key auto_increment,
    user_name    varchar(30),
    password     varchar(200),
    balance      decimal,
    phone_number varchar(30)

);

create table if not exists tb_user_address
(
    user_address_id int primary key auto_increment,
    address         varchar(50),
    receive_tel     varchar(50),
    receive_name    varchar(30),
    user_id         int,
    foreign key (user_id) references tb_user (user_id)

);

create table if not exists tb_shop_cart
(
    shop_cart_id int primary key auto_increment,
    user_id      int,
    foreign key (user_id) references tb_user (user_id)
);

create table if not exists tb_business
(
    business_id   int primary key auto_increment,
    business_name varchar(50)
);

create table if not exists tb_order
(
    order_id        int primary key auto_increment,
    order_status    varchar(30),
    user_id         int,
    creat_time      varchar(30),
    user_address_id int,
    total_price     decimal,
    foreign key (user_id) references tb_user (user_id),
    foreign key (user_address_id) references tb_user_address (user_address_id)

);

create table if not exists tb_product
(
    product_id      int primary key auto_increment,
    product_name    varchar(30),
    product_price   int,
    product_img_url varchar(500),
    stock           int,
    business_id     int,
    foreign key (business_id) references tb_business (business_id)

);

create table if not exists tb_product_item
(
    product_item_id int primary key auto_increment,
    product_id      int,
    detail_1        varchar(300),
    detail_2        varchar(300),
    detail_3        varchar(300),
    detail_4        varchar(300),
    detail_5        varchar(300),
    detail_6        varchar(300),
    foreign key (product_id) references tb_product (product_id)


);

create table if not exists tb_product_img_list
(
    img_id          int primary key auto_increment,
    img_url         varchar(500),
    product_item_id int,
    foreign key (product_item_id) references tb_product_item (product_item_id)
);

create table if not exists tb_order_item
(
    order_item_id  int primary key auto_increment,
    order_id       int,
    product_id     int,
    num            int,
    traffic_number int,
    foreign key (order_id) references tb_order (order_id),
    foreign key (product_id) references tb_product (product_id)

);

create table if not exists tb_comment
(
    comment_id      int primary key auto_increment,
    comment_level   int,
    comment_detail  varchar(1000),
    comment_time    varchar(50),
    product_item_id int,
    user_id         int,
    foreign key (product_item_id) references tb_product_item (product_item_id),
    foreign key (user_id) references tb_user (user_id)

);

create table if not exists tb_cart_item
(
    cart_item_id int primary key auto_increment,
    shop_cart_id int,
    num int,
    product_id int,
    foreign key (shop_cart_id)references tb_shop_cart(shop_cart_id),
    foreign key (product_id)references tb_product(product_id)

);

create table baseSelect
(
    product_id int,
    product_name varchar(30),
    product_price int,
    product_img_url varchar(500),
    business_name varchar(50),
    comment_count int,
    foreign key (product_id) references tb_product(product_id)

);

create trigger base after insert on tb_product
    for each row
    insert into baseSelect(product_id, product_name, product_price, product_img_url, business_name, comment_count)
    select x.product_id as product_id,x.product_name,x.product_price,x.product_img_url,x.business_name,0
    from (select p.product_id,p.product_name,p.product_price,p.product_img_url,b.business_name from tb_product p,tb_business b where p.business_id=b.business_id ) as x ;

insert into tb_product values (3,'小灵通',20,'',50,1);

drop trigger base;

drop table baseSelect;
```




