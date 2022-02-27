package com.midas.springjpa.web.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = NoEmojiValidator.class)
@Documented
public @interface NoEmoji {
  String message() default "{validation.NoEmoji.message}";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
