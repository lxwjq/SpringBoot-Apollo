package cn.thislx.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author: LX 17839193044@162.com
 * @Description: 启动类必须增加@EnableApolloConfig
 * @Date: 2019/4/16 20:03
 * @Version: V1.0
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableApolloConfig
public class ApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
    }

}
