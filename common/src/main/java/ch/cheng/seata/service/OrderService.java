package ch.cheng.seata.service;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param userId    用户id
     * @param productId 商品id
     * @param price     价格
     * @return 订单编号
     */
    Integer createOrder(Long userId, Long productId, Integer price) throws Exception;
}
