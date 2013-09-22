package net.hubtangle.model.exception

import org.springframework.validation.Errors;

/**
 * Thrown due to validation errors on domain classes
 * @author mkubryn
 *
 */
class ModelValidationException extends Exception {
	def modelBean
}
