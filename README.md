# Spring-Cloud-Seata-Demo
本项目基于SpringCloud和Seata实现简单的分布式事务

在order下订单，先扣减商品库存，再扣减用户余额，最后生成订单

由order模块开启全局事务，生成xid，并在各个模块中传递，通过feign调用其他模块的接口。

xid的传递需要手动配置。在请求模块的请求头中添加xid，在被调用模块中加载xid。
### 模块功能介绍
1. common
    * 存放一些公用类

2. order
    * 下订单，对外提供接口
    
3. account
    * 扣减用户余额
    
4. product
    * 扣减商品库存

数据库位于db.sql文件中，导入mysql即可。需要修改各服务的数据源及seata配置。

注意：seata.tx-service-group:xxx 和 seata.service.vgroup-mapping.xxx:default 中的xxx需要相同