package etu2061.framework.annotation;

import java.lang.annotation.*;
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlAnnotation {
    String url();
}
