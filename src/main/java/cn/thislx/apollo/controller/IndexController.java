package cn.thislx.apollo.controller;


import cn.thislx.apollo.utils.PropertiesUtils;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.Set;

/**
 * @Author: LX 17839193044@162.com
 * @Description: 测试获取apollo配置controller
 * @Date: 9:36 2019/4/16
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/apollo")
public class IndexController {


    /**
     * 可以直接使用注解获取apollo中的配置信息
     */
    @Value("${server.port}")
    private String port;

    /**
     * 从apollo获取配置信息
     */
    @ApolloConfig
    private Config config;

    /**
     * @Author: LX 17839193044@162.com
     * @Description: 打印apollo的索引配置类
     * @Date: 2019/4/16 19:59
     * @Version: V1.0
     */
    @GetMapping("/index")
    public Properties apolloReadDemo() {
        /**
         * 得到当前app.id中的配置
         * */
        Set<String> set = config.getPropertyNames();
        for (String key : set) {
            PropertiesUtils.properties.setProperty(key, config.getProperty(key, null));
        }
        for (String key : PropertiesUtils.properties.stringPropertyNames()) {
            System.out.println(key + ":" + PropertiesUtils.properties.getProperty(key));
        }
        return PropertiesUtils.properties;
    }


    /**
     * @Author: LX 17839193044@162.com
     * @Description: 测试修改apollo配置文件热更新
     * @Date: 2019/4/16 19:54
     * @Version: V1.0
     */
    @GetMapping("testHotUpdate")
    public String testHotUpdate() {
        log.debug("debug log...");
        log.info("info log...");
        log.warn("warn log...");
        return "port:" + port;
    }
}
