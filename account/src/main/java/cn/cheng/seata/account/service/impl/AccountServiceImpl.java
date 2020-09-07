package cn.cheng.seata.account.service.impl;

import ch.cheng.seata.service.AccountService;
import cn.cheng.seata.account.mapper.AccountMapper;
import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Cheng Mingwei
 * @create 2020-09-03 14:22
 **/
@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Boolean reduceBalance(Long userId, Integer price) throws Exception {
        log.info("[reduceBalance] 当前 XID: {}", RootContext.getXID());
        int i = accountMapper.selectBalance(userId, price);
        if (i == 0) {
            throw new RuntimeException("余额不足");
        }
        int money = accountMapper.reduceBalance(userId, price);
        if (money == 0) {
            throw new Exception("扣减用户余额失败");
        }
        return true;
    }
}
