package cn.cheng.seata.account;

import ch.cheng.seata.config.DruidConfig;
import ch.cheng.seata.config.SeataXidFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Cheng Mingwei
 * @create 2020-09-03 14:14
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ImportAutoConfiguration(classes = {DruidConfig.class, SeataXidFilter.class})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class);
    }

}
