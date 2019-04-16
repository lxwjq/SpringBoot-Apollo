package cn.thislx.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @Author: LX 17839193044@162.com
 * @Description: 监听apollo配置更新配置类
 * @Date: 18:45 2019/4/16
 * @Version: V1.0
 */
@Configuration
public class LoggerConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggerConfig.class);
    private static final String LOGGER_TAG = "logging.level.";

    @Autowired
    private LoggingSystem loggingSystem;

    /**
     * @ApolloConfig注解： 将Apollo服务端的中的配置注入这个类中。
     */
    @ApolloConfig
    private Config config;

    /**
     * @param changeEvent:可以获取被修改配置项的key集合，以及被修改配置项的新值、旧值和修改类型等信息。
     * @ApolloConfigChangeListener注解： 监听配置中心配置的更新事件，若该事件发生，则调用refreshLoggingLevels方法，处理该事件。
     */
    @ApolloConfigChangeListener
    private void configChangeListter(ConfigChangeEvent changeEvent) {
        refreshLoggingLevels();
    }


    /**
     * @Author: LX 17839193044@162.com
     * @Description: apollo文件修改执行。实现实时更新日志打印级别
     * @Date: 2019/4/16 20:11
     * @Version: V1.0
     */
    @PostConstruct
    private void refreshLoggingLevels() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
            if (StringUtils.containsIgnoreCase(key, LOGGER_TAG)) {
                String strLevel = config.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
                logger.info("{}:{}", key, strLevel);
            }
        }
    }

}
