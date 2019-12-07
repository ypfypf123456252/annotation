package com.atguigu.test;


import com.atguigu.bean.Blue;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class IOCTest {
    private ApplicationContext applicationContext=new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test01(){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();//容器中所有的组件
        for (String name:definitionNames){
            System.out.println(name);
        }

    }
    @Test
    public void test02(){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();//容器中所有的组件
        for (String name:definitionNames){
            System.out.println(name);
        }
//        默认是单实例的
        Object person = applicationContext.getBean("person");
        Object person1 = applicationContext.getBean("person");
        System.out.println(person==person1);
    }
    @Test
    public void test03(){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(MainConfig2.class);
        Environment environment = applicationContext.getEnvironment();//获取ioc环境
        //获取环境变量的值:Windows 10
        String property = environment.getProperty("os.name");
        System.out.println(property);
        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name:namesForType){
            System.out.println(name);
        }

        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    @Test
    public void test04Import(){

        printBeans((AnnotationConfigApplicationContext) applicationContext);
        Blue blue=applicationContext.getBean(Blue.class);
        System.out.println(blue);
    }
    private void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name:definitionNames){
            System.out.println(name);
        }
    }
}
