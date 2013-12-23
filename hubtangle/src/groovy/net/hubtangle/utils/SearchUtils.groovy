package net.hubtangle.utils

import net.hubtangle.api.search.Indexed

import java.lang.reflect.Field

/**
 * Utilities for searching purposes
 *
 * Created by mkubryn on 20.12.13.
 */
class SearchUtils {

    /**
     * Returns map of properties annotated with Indexed annotation
     *
     * @param obj
     * @param annotationClass
     * @return map
     */
    static def getSeachableProperties(Object obj) {
        return findAllPropertiesWithAnnotationForClassHierarhy(obj)
    }

    private static def findAllPropertiesWithAnnotationForClassHierarhy(Object obj) {
        Map properties = findAllPropertiesForClassWithAnotation(obj, obj.class)

        for (Class<?> c = obj.class; c != null; c = c.getSuperclass()) {
            def superProperties = findAllPropertiesForClassWithAnotation(obj, c)
            properties.putAll(superProperties)
        }

        return properties;
    }

    private static def findAllPropertiesForClassWithAnotation(Object obj, Class objClass) {
        def toReturnProperties = [:]
        obj.properties.each { prop ->
            objClass.declaredFields.each { Field field ->
                if(field.name == prop.key && Indexed in field.declaredAnnotations*.annotationType()) {
                    Indexed indexed = field.getAnnotation(Indexed)
                    def indexedFieldName = indexed.field()

                    // check if field is set
                    if(indexedFieldName != Indexed.NO_VALUE) {
                        toReturnProperties[indexedFieldName] = prop.value
                    } else {
                        toReturnProperties[prop.key] = prop.value
                    }
                }
            }
        }

        toReturnProperties
    }
}