package com.xxx.common.annotation;

import java.lang.annotation.*;

/**
 * @Author
 * @Date 2022/8/31 18:37
 * @PackageName:com.xxx.common.annotation
 * @ClassName: CSVField
 * @Description: 自定义注解，读取属性值与MySQL的字段对应
 * @Version 1.0
 */
@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CSVField {

     String name() default "";
}
