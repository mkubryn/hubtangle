package net.hubtangle.api.security.acl

/**
 * Enumerates various relations between things in Hubtangle
 */
public enum Relations {
    /**
     * Is subscribing
     * Is subscribed by
     */
    SUBSCRIPTION,

    /**
     * Is moderator
     * Is moderated by
     */
    MODERATION,

    /**
     * Can read
     * Is readable by
     */
    READABLE,

    /**
     * Can write
     * Is writable
     */
    WRITABLE,

    /**
     * Has tag
     * Is tagged with
     */
    TAG,

}