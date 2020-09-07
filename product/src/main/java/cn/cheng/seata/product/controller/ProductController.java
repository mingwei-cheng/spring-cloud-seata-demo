package cn.cheng.seata.product.controller;

import ch.cheng.seata.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Cheng Mingwei
 * @create 2020-09-02 15:51
 **/
@Log4j2
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/reduce")
    public Boolean reduceProduct(Long productId, Integer amount) throws Exception {
        log.info("收到扣减库存请求，商品id：{},商品数量：{}", productId, amount);
        return productService.reduceStock(productId, amount);
    }


}

