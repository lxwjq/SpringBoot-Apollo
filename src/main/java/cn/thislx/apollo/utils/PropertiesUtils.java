package cn.thislx.apollo.utils;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.Properties;

/**
 * @Author: LX 17839193044@162.com
 * @Description: 读取公共apollo配置
 * @Date: 2019/4/16 20:03
 * @Version: V1.0
 */
public class PropertiesUtils {
    private static final String COMMON = "nova1.NovaCommon";
    public static Properties properties = new Properties();

    static {
        Config commonConfig = ConfigService.getConfig(COMMON);
        if (commonConfig != null) {
            for (String key : commonConfig.getPropertyNames()) {
                properties.setProperty(key, commonConfig.getProperty(key, null));
            }
        }
    }
}
