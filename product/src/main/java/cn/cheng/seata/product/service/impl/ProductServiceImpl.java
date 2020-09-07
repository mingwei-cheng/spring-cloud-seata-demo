package cn.cheng.seata.product.service.impl;

import ch.cheng.seata.service.ProductService;
import cn.cheng.seata.product.mapper.ProductMapper;
import io.seata.core.context.RootContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Cheng Mingwei
 * @create 2020-09-02 15:50
 **/
@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reduceStock(Long productId, Integer amount) throws Exception {
        log.info("[reduceStock] 当前 XID: {}", RootContext.getXID());
        int i = productMapper.selectStock(productId, amount);
        if (i == 0) {
            throw new Exception("库存不足");
        }
        int stock = productMapper.reduceStock(productId, amount);
        if (stock == 0) {
            throw new Exception("扣减库存失败");
        }
        return true;
    }
}
