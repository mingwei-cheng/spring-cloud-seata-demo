package cn.cheng.seata.product.mapper;

import org.apache.ibatis.annotations.*;

/**
 * @author Cheng Mingwei
 * @create 2020-09-01 18:00
 **/
@Mapper
public interface ProductMapper {

    /**
     * 查询剩余库存是否大于本次需扣除数量
     *
     * @param productId 商品编号
     * @param amount    扣减数量
     * @return 0小于 1大于
     */
    @Select("SELECT (SELECT stock from product where id = #{productId}) >= #{amount}")
    int selectStock(Long productId, Integer amount);

    /**
     * 扣减库存
     *
     * @param productId 商品编号
     * @param amount    扣减数量
     * @return 影响记录行数
     * @throws Exception
     */
    @Update("UPDATE product SET stock = stock - #{amount} WHERE id = #{productId} AND stock >= #{amount}")
    int reduceStock(Long productId, Integer amount) throws Exception;

}
