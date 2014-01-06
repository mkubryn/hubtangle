package net.hubtangle.utils
/**
 * Created by mkubryn on 06.01.14.
 */
class CommandUtils {

    static final String ENTITY_ID_PROP = 'entityId'
    static final String ID_PROP = 'id'


    private CommandUtils() {
    }

    static def copyProperties(Object from, Object to) {
        def cmdProperties = to.metaClass.properties.collect { it.name }

        cmdProperties.each { String propertyName ->
            if(from.hasProperty(propertyName)) {
                tryToSetProperty(to, propertyName, from."$propertyName")
            }
        }

        /*
         * Set properties id - entityId
         */
        if(from.hasProperty(ENTITY_ID_PROP) && to.hasProperty(ID_PROP)) {
            tryToSetProperty(to, ID_PROP, from."$ENTITY_ID_PROP")

        }

        if(from.hasProperty(ID_PROP) && to.hasProperty(ENTITY_ID_PROP)) {
            tryToSetProperty(to, ENTITY_ID_PROP, from."$ID_PROP")

        }
    }

    static private tryToSetProperty(Object object, String propertyName, Object propertyValue) {
        try {
            object."$propertyName" = propertyValue
        } catch(Exception e) {
        }
    }
}