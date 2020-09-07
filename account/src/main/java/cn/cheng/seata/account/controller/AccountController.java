package cn.cheng.seata.account.controller;

import ch.cheng.seata.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Cheng Mingwei
 * @create 2020-09-03 14:16
 **/
@Log4j2
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/reduce-balance")
    public Mono<Boolean> reduceBalance(Long userId, Integer price) throws Exception {
        log.info("收到扣减用户：{}余额请求，扣减数量：{}", userId, price);
        return Mono.just(accountService.reduceBalance(userId, price));
    }

}
