package com.eale.rpc.client.config;

import com.eale.rpc.client.annotation.RpcClient;
import com.eale.rpc.client.factory.RpcClientFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Set;

/**
 * @Author Admin
 * @Date 2020/8/19
 * @Description
 * @Version 1.0
 **/

public class RpcInitConfig implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider provider = getScanner();
        // 设置扫描器
        provider.addIncludeFilter(new AnnotationTypeFilter(RpcClient.class));
        // 扫描此包下的所有带有rpcClient注解
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("com.eale.rpc.client");
        for (BeanDefinition beanDefinition : beanDefinitionSet) {
            if (beanDefinition instanceof AnnotatedBeanDefinition){
                // 获取注解上的参数
                AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
                String beanClassAllName = beanDefinition.getBeanClassName();
                Map<String, Object> annotationAttributes = annotatedBeanDefinition.getMetadata().getAnnotationAttributes(RpcClient.class.getCanonicalName());
                // 将RpcClient的工厂类注册进
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RpcClientFactoryBean.class);
                // 设置RPCClientFactoryBean工厂类的构造函数的值
                builder.addConstructorArgValue(beanClassAllName);
                builder.getBeanDefinition().setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                registry.registerBeanDefinition(beanClassAllName, builder.getBeanDefinition());
            }

        }


    }

    /**
     * 允许spring扫描接口上的注解
     * @return
     */
    private ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false){
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface()&&beanDefinition.getMetadata().isIndependent();
            }
        };
    }
}
