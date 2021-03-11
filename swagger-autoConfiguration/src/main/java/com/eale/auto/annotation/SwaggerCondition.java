package com.eale.auto.annotation;

import com.eale.auto.condition.OnSwaggerCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSwaggerCondition.class)
public @interface SwaggerCondition {
}
