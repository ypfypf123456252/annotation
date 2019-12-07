package com.atguigu.config;

import com.atguigu.bean.Color;
import com.atguigu.bean.Person;
import com.atguigu.bean.Red;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegistrar;
import com.atguigu.condition.MyImportSelector;
import com.atguigu.condition.WindowsCondition;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.*;

/**
 * Scope:调整作用域
 * prototype:多实例中:每次获取的时候才会调用方法创建对象,每次获取都会调一遍
 * singleton:单实例的(默认值):IOC容器启动会调用方法创建对象到IOC容器中
 *      以后每次获取就是从容器(map.get())中拿
 * 注:配置xml的形式中,不会创建对象到IOC容器中,懒
 *
 * 懒加载:
 *      单实例bean:默认在容器启动的时候创建对象
 *      懒加载:容器启动不创建对象.第一次使用(获取)Bean创建对象,并初始化
 */
//类中组件统一设置.满足当前条件,这个类中配置的所有bean注册才能生效
@Conditional({WindowsCondition.class})
//@Conditional({LinuxCondition.class})
@Configuration
//导入组件,id默认是组件的全类名
@Import({Color.class, Red.class, MyImportSelector.class,
        MyImportBeanDefinitionRegistrar.class})//快速导入组件
public class MainConfig2 {

    //默认是单实例的
//    @Scope("prototype")
    @Lazy
    @Bean(value = "person")
    public Person person(){
        System.out.println("给容器中添加Person....");
        return new Person("张三",25);
    }

    /**
     * @conditional({Condition}):按照一定的条件进行判断,满足条件给容器中注册bean
     * 如果系统是windows,给容器中注册("bill")
     * 如果系统是Linux,给容器中注册("linus")
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){
        return new Person("Bill Gates",62);

    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02(){
        return new Person("linus",48);
    }

    /**
     * 给容器中注册组件:
     *  1).包扫描+组件标注注解(@Controller/@Service/@Component):自己写的类
     *  2).@Bean[导入的第三方包里面的组件]
     *  3).@import[快速给容器中倒入一个组件]
     *      1).@Import(要导入到容器中的组件):容器中就会自动注册这个组件,ID默认是全类名
     *      2)ImportSelector:返回需要导入的组件的全类名数组-->springboot常用这种
     *      3)ImportBeanDefinitionRegistrar:手动注册bean到容器中
     *  4).使用spring提供的FactoryBean(工厂Bean)
     *
     */
}
