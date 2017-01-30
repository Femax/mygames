package com.mygames.mygames.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Max on 30.01.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BaseActivityOptions {

    int layout() default 0;

    String activityName() default "Primary";

    boolean knifeEnabled() default false;

    boolean eventBusEnabled() default false;

    boolean menuEnabled() default false;

    int toolbarResId() default 0;

    boolean isHomeAsUp() default false;
}
