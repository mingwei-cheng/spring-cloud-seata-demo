package cn.cheng.seata.order.controller;

import ch.cheng.seata.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Cheng Mingwei
 * @create 2020-09-01 17:55
 **/
@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @ResponseBody
    public Mono<Integer> createOrder(@RequestBody Map<String, Object> map) throws Exception {
        Long userId = Long.parseLong(String.valueOf(map.get("userId")));
        Long productId = Long.parseLong(String.valueOf(map.get("productId")));
        Integer price = (Integer) map.get("price");
        log.info("[createOrder] 收到下单请求,用户:{}, 商品:{}, 价格:{}", userId, productId, price);
        return Mono.just(orderService.createOrder(userId, productId, price));
    }

}
