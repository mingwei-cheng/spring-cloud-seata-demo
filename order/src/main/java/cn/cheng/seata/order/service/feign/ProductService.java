package cn.cheng.seata.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@FeignClient(name = "product-service", fallback = Exception.class)
public interface ProductService {

    /**
     * 扣减库存
     *
     * @param productId 商品 ID
     * @param amount    扣减数量
     */
    @PostMapping(value = "/product/reduce")
    Boolean reduceStock(@RequestParam("productId") Long productId, @RequestParam("amount") Integer amount);

}
