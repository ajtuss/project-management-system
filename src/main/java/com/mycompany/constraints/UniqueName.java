package com.mycompany.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {

    String message() default "Name must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
