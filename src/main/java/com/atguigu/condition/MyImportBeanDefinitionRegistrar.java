package com.atguigu.condition;

import com.atguigu.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * AnnotationMetadata:当前类的注解信息
     * BeanDefinitionRegistry: BeanDefinition注册类
     *      把所有需要添加到容器中的bean:调用
     *      BeanDefinitionRegistry.registerBeanDefinition手工注册进来
     */
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        boolean definition1 = beanDefinitionRegistry.containsBeanDefinition("com.atguigu.bean.Red");
        boolean definition2 = beanDefinitionRegistry.containsBeanDefinition("com.atguigu.bean.Blue");
        if (definition1 && definition2){
            //指定bean定义信息:(bean的类型,bean的作用域...)
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            //注册一个bean,指定bean名
            beanDefinitionRegistry.registerBeanDefinition("rainBow",rootBeanDefinition);
        }
    }
}
