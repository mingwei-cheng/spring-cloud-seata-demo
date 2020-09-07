package ch.cheng.seata.service;

public interface AccountService {
    /**
     * 扣减用户余额
     *
     * @param userId 用户id
     * @param price  价格
     */
    Boolean reduceBalance(Long userId, Integer price) throws Exception;

}
