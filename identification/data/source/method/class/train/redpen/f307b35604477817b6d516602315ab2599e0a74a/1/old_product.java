public static Validator createValidator(
      String validatorType,
      ValidatorConfiguration conf,
      CharacterTable charTable) throws DocumentValidatorException {
    Validator validator;
    // @todo accept plug-in validators.
    if (validatorType.equals("SentenceIterator")) {
      validator = new SentenceIterator();
    } else if (validatorType.equals("SectionLength")) {
      validator = new SectionLengthValidator();
    } else if (validatorType.equals("MaxParagraphNumber")) {
      validator = new ParagraphNumberValidator();
    } else if (validatorType.equals("ParagraphStartWith")) {
      validator = new ParagraphStartWithValidator();
    } else {
      throw new DocumentValidatorException(
          "There is no Validator like " + validatorType);
    }
    validator.loadConfiguration(conf, charTable);
    return validator;
  }