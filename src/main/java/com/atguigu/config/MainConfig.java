package com.atguigu.config;

import com.atguigu.Service.BookService;
import com.atguigu.bean.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * useDefaultFilters = false禁用默认规则,includeFilters才能生效
 * ComponentScans可以写多个ComponentScan
 * 扫描规则:
 *  FilterType.ANNOTATION按照注解
 *  FilterType.ASSIGNABLE_TYPE按照给定的类型,包括他的子类或者实现类都会被加载进来
 *  FilterType.REGEX:使用正则指定
 *  FilterType.CUSTOM:使用自定义规则
 */
//配置类==配置文件
@Configuration  //告诉spring这是一个配置类
//value:指定要扫描的包
@ComponentScan(value = "com.atguigu",includeFilters ={
        //Filter指定扫描规则,这里按照注解排除
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,
//                classes = {Controller.class}),
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
//                classes = {BookService.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM,
                classes = {MyTypeFilter.class})
},useDefaultFilters = false)
//excludeFilters=Filter[],指定扫描的时候按照什么规则排除哪些组件
//includeFilters=Filter[],指定扫描的时候只需要包含哪些组件
public class MainConfig {
    //给容器中注册一个bean;类型为返回值的类型,id默认是用方法名作为id
    @Bean(value = "person")
    public Person person01(){
        return new Person("lisi",20);
    }
}
