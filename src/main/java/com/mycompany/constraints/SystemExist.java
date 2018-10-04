package com.mycompany.constraints;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = SystemExistValidator.class)
@Documented
public @interface SystemExist {

    String message() default "{constraints.SystemExist.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String systemName();

    String systemId();

}
