package com.eale.generate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/8/23
 * @Version 1.0
 **/
public class PropUtils extends PropertyPlaceholderConfigurer{

    private static Logger logger = LoggerFactory.getLogger(PropUtils.class);
    private static Map staticPropertiesMap;
    private String[] remotes;

    public PropUtils() {
    }

    public String[] getRemotes() {
        return this.remotes;
    }

    public void setRemotes(String[] remotes) {
        this.remotes = remotes;
    }

    public static Object getContextProperty(String name) {
        return staticPropertiesMap.get(name);
    }

    private void printRemoteFiles() {
        String[] arr$ = this.remotes;
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String str = arr$[i$];
            logger.info(String.format("Init remote properties: [%s]", str));
        }

    }

    private void closeStream(Closeable[] closeables) {
        Closeable[] arr$ = closeables;
        int len$ = closeables.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Closeable c = arr$[i$];
            if (c != null) {
                try {
                    c.close();
                } catch (IOException var7) {
                    logger.warn("Exception in closing " + c, var7);
                }
            }
        }

    }

    public void readRemoteFile(String rurl, Properties pros) {
        try {
            InputStream is = null;
            URL url = new URL(rurl);
            is = url.openStream();
            pros.load(is);
            this.closeStream(new Closeable[]{is});
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties pros) throws BeansException {
        if (this.remotes != null && this.remotes.length > 0) {
            this.printRemoteFiles();
            String[] arr$ = this.remotes;
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String url = arr$[i$];
                this.readRemoteFile(url, pros);
            }
        }

        super.processProperties(beanFactory, pros);
        staticPropertiesMap = new HashMap();
        Iterator i$ = pros.keySet().iterator();

        while(i$.hasNext()) {
            Object key = i$.next();
            String keyStr = key.toString();
            String value = pros.getProperty(keyStr);
            staticPropertiesMap.put(keyStr, value);
        }

    }

}
