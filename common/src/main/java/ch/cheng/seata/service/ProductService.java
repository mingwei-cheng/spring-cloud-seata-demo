package ch.cheng.seata.service;

public interface ProductService {

    /**
     * 扣减库存
     *
     * @param productId 商品 ID
     * @param amount    扣减数量
     */
    Boolean reduceStock(Long productId, Integer amount) throws Exception;

}
