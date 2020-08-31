package com.eale.generate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @description: 启动配置加载, properties
 * @author: cao.weiwei
 * @create: 2018-05-24 17:08
 */
@Configuration
public class ApplicationConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ApplicationConfigurer.class);
    public static final String SPRING_CONFIG_LOCATION = "spring.config.location";
    /**
     * 自定义配置加载，方法定义为static的，保证优先加载
     * @return
     */
    @Bean
    public static PropertyPlaceholderConfigurer properties() throws IOException {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream inputStream = ApplicationConfigurer.class.getClassLoader().getResourceAsStream("application.properties");
        InputStream in = null;
        String s = readStream(inputStream);
        boolean prodFlag = false;
        boolean testFlag = false;
        if (s.contains("prod")) {
            in = ApplicationConfigurer.class.getClassLoader().getResourceAsStream("application-prod.properties");
            prodFlag = true;
        } else if (s.contains("test")) {
            in = ApplicationConfigurer.class.getClassLoader().getResourceAsStream("application-test.properties");
            testFlag = true;
        }
        // 使用properties对象加载输入流
        properties.load(in);
        //获取key对应的value值
        String jdbcProperties = properties.getProperty("jdbcProperties");
        final PropUtils ppc = new PropUtils();//自定义PropertyPlaceholderConfigurer
        ppc.setOrder(1);
        ppc.setIgnoreResourceNotFound(true);
        final List<Resource> resourceLst = new ArrayList<Resource>();
        if (System.getProperty(SPRING_CONFIG_LOCATION) != null) {
            String configFilePath = System.getProperty(SPRING_CONFIG_LOCATION);
            String[] configFiles = configFilePath.split(",|;");
            FileSystemResource res = null;
            for (String configFile : configFiles) {
                if (configFile.startsWith("file:")) {
                    resourceLst.add(new FileSystemResource(configFile));
                } else {
                    resourceLst.add(new ClassPathResource(configFile));
                }
            }
        } else {
            if (prodFlag) {
                resourceLst.add(new ClassPathResource("application-prod.properties"));
            }
            if (testFlag) {
                resourceLst.add(new ClassPathResource("application-test.properties"));
            }
            //resourceLst.add(new ClassPathResource("jdbc.properties"));
        }
        ppc.setLocations(resourceLst.toArray(new Resource[]{}));
        ppc.setRemotes(new String[]{jdbcProperties});
        return ppc;
    }
    /**
     * 读取 InputStream 到 String字符串中
     */
    public static String readStream(InputStream in) {
        try {
            //<1>创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //<2>创建缓存大小
            byte[] buffer = new byte[1024]; // 1KB
            //每次读取到内容的长度
            int len = -1;
            //<3>开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
            }
            //<4> 把字节数组转换为字符串
            String content = baos.toString();
            //<5>关闭输入流和输出流
            in.close();
            baos.close();
            //<6>返回字符串结果
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
