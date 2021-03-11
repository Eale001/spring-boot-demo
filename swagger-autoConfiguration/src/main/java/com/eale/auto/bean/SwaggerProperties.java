package com.eale.auto.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author Admin
 * @Date 2020/10/14
 * @Description //TODO
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "swagger")
@EnableSwagger2
public class SwaggerProperties {

    private String basePackage;

    private String title = "API";

    private String description;

    private String url;

    private String contact = "admin";

    private String version;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
