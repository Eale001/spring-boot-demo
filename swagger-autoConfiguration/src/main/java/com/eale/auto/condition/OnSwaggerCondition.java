package com.eale.auto.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author Admin
 * @Date 2020/10/14
 * @Description //TODO
 * @Version 1.0
 **/
public class OnSwaggerCondition implements Condition {


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String basePackage = conditionContext.getEnvironment().getProperty("basePackage");
        if (basePackage == null){
            throw new RuntimeException("basePackage 不能为空");
        }else {
            return true;
        }
    }
}
