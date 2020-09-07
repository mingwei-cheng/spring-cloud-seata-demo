package cn.cheng.seata.order.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@FeignClient(name = "account-service")
public interface AccountService {
    /**
     * 扣减用户余额
     *
     * @param userId 用户id
     * @param price  价格
     */
    @PostMapping("/account/reduce-balance")
    Boolean reduceBalance(@RequestParam("userId") Long userId, @RequestParam("price") Integer price);

}
