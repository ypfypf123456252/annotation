package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//判断是否Windows系统
public class WindowsCondition implements Condition {
    /**
     * ConditionContext:判断条件能使用的上下文(环境)
     * AnnotatedTypeMetadata:注释信息
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //是否Linux系统
        //1.能获取到IOC使用的beanfactory工厂
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        //2.获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //3.获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //4.获取到bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        //可以判断容器中的bean注册情况,也可以给容器中注册bean
        boolean person = registry.containsBeanDefinition("bill");//????????不懂

        String property = environment.getProperty("os.name");
        if (property.contains("Window")){
            return true;
        }
        return false;
    }
}
