package cn.cheng.seata.order.service.impl;

import ch.cheng.seata.service.OrderService;
import cn.cheng.seata.order.mapper.OrderMapper;
import cn.cheng.seata.order.pojo.OrderInfo;
import cn.cheng.seata.order.service.feign.AccountService;
import cn.cheng.seata.order.service.feign.ProductService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author Cheng Mingwei
 * @create 2020-09-01 17:58
 **/
@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductService productService;
    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "create-order", rollbackFor = Exception.class)
    public Integer createOrder(Long userId, Long productId, Integer price) {
        Integer amount = 1;
        String xid = GlobalTransactionContext.getCurrentOrCreate().getXid();
        RootContext.bind(xid);
        log.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        log.info("------开始扣减库存------");
        Boolean isReduceStock = productService.reduceStock(productId, amount);
        if (!isReduceStock) {
            throw new RuntimeException("扣减库存失败，进行回滚");
        }
        log.info("------扣减库存成功------");

        log.info("------开始扣减用户余额------");
        Boolean isReduceBalance = accountService.reduceBalance(userId, amount * price);
        if (!isReduceBalance) {
            throw new RuntimeException("扣减库存失败，进行回滚");
        }
        log.info("------扣减用户余额成功------");

        log.info("------开始保存订单------");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setProductId(productId);
        orderInfo.setPayAmount(price * amount);
        orderMapper.saveOrder(orderInfo);
        log.info("保存订单成功,订单id：{}", orderInfo.getId());

        return orderInfo.getId();
    }
}
