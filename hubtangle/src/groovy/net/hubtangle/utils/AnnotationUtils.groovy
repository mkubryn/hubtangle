package net.hubtangle.utils

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile
import groovy.transform.CompileStatic

import java.lang.reflect.Field

/**
 * Utils for handling annotations
 *
 * Created by mkubryn on 20.12.13.
 */
class AnnotationUtils {

    /**
     * Returns map of properties annotated with passed annotation class in given object
     * @param obj
     * @param annotationClass
     * @return map
     */
    static def getAnnotatedProperties(Object obj, Class annotationClass) {
        return findAllPropertiesWithAnnotationForClassHierarhy(obj, annotationClass)
    }

    private static def findAllPropertiesWithAnnotationForClassHierarhy(Object obj, Class annotClass) {
        Map properties = findAllPropertiesForClassWithAnotation(obj, obj.class, annotClass)

        for (Class<?> c = obj.class; c != null; c = c.getSuperclass()) {
            def superProperties = findAllPropertiesForClassWithAnotation(obj, c, annotClass)
            properties.putAll(superProperties)
        }

        return properties;
    }

    private static def findAllPropertiesForClassWithAnotation(Object obj, Class objClass, Class annotClass ) {
        obj.properties.findAll { prop ->
            objClass.declaredFields.find { Field field ->
                field.name == prop.key && annotClass in field.declaredAnnotations*.annotationType()
            }
        }
    }
}