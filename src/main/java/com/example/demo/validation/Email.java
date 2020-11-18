package com.example.demo.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Pattern(regexp = ".+@.+\\..+")
@Constraint(validatedBy = {})
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)

public @interface Email {
	String message() default "Bad format email address";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}