package hubtangle

import net.hubtangle.security.acl.AclSecured

/**
 * Service handling ACL management during save/get/find operation on domain classes
 *
 * Other services may extend this service or use it a delegate.
 */
class AclAwareService {

    def aclService

    def save(Object model) {
        if(!isSecured(model)) {
            model.save()
        }
    }

    def get(type, id) {
        Class aClass

    }

    private isSecured(clazz) {
        clazz.isAnnotationPresent(AclSecured.class)
    }
}
