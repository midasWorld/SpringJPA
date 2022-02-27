package com.midas.springjpa.web.valid;

import com.midas.springjpa.web.dto.member.SignUpRequestDto;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoEmojiValidator
        implements ConstraintValidator<NoEmoji, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (StringUtils.isEmpty(value)) return true;

    return EmojiParser.parseToAliases(value).equals(value);
  }
}
