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
    READ,

    /**
     * Can write
     * Is writable
     */
    WRITE,

    /**
     * Has tag
     * Is tagged with
     */
    TAG,

    /**
     * Is a member of
     * Has a member
     */
    MEMBER,
}