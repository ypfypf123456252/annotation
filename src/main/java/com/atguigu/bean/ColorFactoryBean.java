package com.atguigu.bean;

import org.springframework.beans.factory.FactoryBean;
//创建一个spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean {

    //返回一个Color对象,这个对象会添加到容器中
    public Color getObject() throws Exception {
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    //控制是否为单例
    public boolean isSingleton() {
        return true;
    }
}
