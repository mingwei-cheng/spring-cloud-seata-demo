package cn.cheng.seata.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {

    /**
     * 查询用户余额是否大于本次需扣减的价格
     *
     * @param userId 用户id
     * @param price  价格
     * @return 0小于 1大于
     */
    @Select("SELECT (SELECT balance from account where id=#{userId}) >= #{price}")
    int selectBalance(Long userId, Integer price);

    /**
     * 扣减用户余额
     *
     * @param userId 用户id
     * @param price  价格
     * @return 扣减结果
     * @throws Exception
     */
    @Update("UPDATE account SET balance= balance - #{price} where id=#{userId} AND balance >= #{price}")
    int reduceBalance(Long userId, Integer price) throws Exception;

}
