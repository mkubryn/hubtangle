package net.hubtangle.api.search

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Annotation marking fields as indexed by SearchService
 *
 * Created by mkubryn on 20.12.13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Indexed {

    public static final String NO_VALUE = "!-x none"

    String field() default "!-x none" //WTF it's not accepting null nor constant!
}