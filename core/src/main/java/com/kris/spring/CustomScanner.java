package com.kris.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * @Program: kris-rpc
 * @Description:
 * @Author: kris
 * @Create: 2025-03-17 21:14
 **/

public class CustomScanner extends ClassPathBeanDefinitionScanner {

    public CustomScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> annoType) {
        super(registry);
        super.addIncludeFilter(new AnnotationTypeFilter(annoType));
    }

    @Override
    public int scan(String... basePackages) {
        return super.scan(basePackages);
    }
}
