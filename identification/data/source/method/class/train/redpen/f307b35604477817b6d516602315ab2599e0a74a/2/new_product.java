public static Validator createValidator(
      String validatorType,
      ValidatorConfiguration conf,
      CharacterTable charTable) throws DocumentValidatorException {

//    Validator validator;
//    // @todo accept plug-in validators.
//    if (validatorType.equals("SentenceIterator")) {
//      validator = new SentenceIterator();
//    } else if (validatorType.equals("SectionLength")) {
//      validator = new SectionLengthValidator();
//    } else if (validatorType.equals("MaxParagraphNumber")) {
//      validator = new ParagraphNumberValidator();
//    } else if (validatorType.equals("ParagraphStartWith")) {
//      validator = new ParagraphStartWithValidator();
//    } else {
//      throw new DocumentValidatorException(
//          "There is no Validator like " + validatorType);
//    }
//
//    // FIXME: Should be removed as refactoring progresses
//    ((ConfigurationLoader) validator).loadConfiguration(conf, charTable);
//    return validator;
    
    throw new RuntimeException("Not yet refactored");
  }