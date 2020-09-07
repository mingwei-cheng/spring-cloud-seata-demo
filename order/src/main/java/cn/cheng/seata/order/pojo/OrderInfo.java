package cn.cheng.seata.order.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Cheng Mingwei
 * @create 2020-09-01 18:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    /**
     * 订单编号
     **/
    private Integer id;

    /**
     * 用户编号
     **/
    private Long userId;

    /**
     * 产品编号
     **/
    private Long productId;

    /**
     * 支付金额
     **/
    private Integer payAmount;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 最后修改时间
     */
    private Date lastUpdateTime;

}
