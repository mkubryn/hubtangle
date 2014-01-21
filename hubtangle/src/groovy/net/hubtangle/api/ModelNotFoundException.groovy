package net.hubtangle.api

/**
 * Thrown when requesting a model object returns nothing
 */
class ModelNotFoundException extends RuntimeException {

    public ModelNotFoundException(String msg) {
        super(msg)
    }
}
