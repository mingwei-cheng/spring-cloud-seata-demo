package cn.cheng.seata.product;

import ch.cheng.seata.config.DruidConfig;
import ch.cheng.seata.config.SeataXidFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Cheng Mingwei
 * @create 2020-09-02 14:50
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ImportAutoConfiguration(classes = {DruidConfig.class, SeataXidFilter.class})
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class);
    }

}
