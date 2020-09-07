package cn.cheng.seata.order.mapper;

import cn.cheng.seata.order.pojo.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author Cheng Mingwei
 * @create 2020-09-01 18:00
 **/
@Mapper
public interface OrderMapper {

    /**
     * 保存订单
     *
     * @param order 订单信息
     * @return 订单id
     */
    @Insert("INSERT INTO orders (user_id, product_id, pay_amount) VALUES (#{userId}, #{productId}, #{payAmount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int saveOrder(OrderInfo order);


}
