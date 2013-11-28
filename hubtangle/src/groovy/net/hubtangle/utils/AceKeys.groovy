package net.hubtangle.utils

/**
 * Created by mkubryn on 18.11.13.
 */
enum AceKeys {

    ENTRY_R(TYPES.R, ENTITIES.ENTRY), HUB_R(TYPES.R, ENTITIES.HUB),
    ENTRY_W(TYPES.W, ENTITIES.ENTRY), HUB_W(TYPES.W, ENTITIES.HUB)

    def TYPES type
    def ENTITIES entity

    public static enum TYPES {
          R, W
    }

    public static enum ENTITIES {
        HUB, ENTRY
    }

    private AceKeys(TYPES type, ENTITIES entity) {
        this.type = type
        this.entity = entity
    }

    def String getAceDiscriminator(entityId) {
        "acl-${entity}-${type}-${entityId}"
    }
}
