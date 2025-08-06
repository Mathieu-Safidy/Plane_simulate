package org.springcopy.annote;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface Length {
    int len() default 255;
    String message() default "La longueur de texte ne doit pas depasser les 255 caracteres";
}