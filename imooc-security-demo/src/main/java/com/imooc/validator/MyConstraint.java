package com.imooc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Skye on 2019/5/9.
 */
@Target({ElementType.METHOD, ElementType.FIELD}) //方法和域
@Retention(RetentionPolicy.RUNTIME) //运行时注解
@Constraint(validatedBy = MyConstrainValidator.class)
public @interface MyConstraint {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
