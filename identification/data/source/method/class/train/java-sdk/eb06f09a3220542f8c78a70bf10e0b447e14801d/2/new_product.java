public TranslationResult translate(final String text, final String source, final String target) {
    Validate.isTrue(source != null && !source.isEmpty(), "source cannot be null or empty");
    Validate.isTrue(target != null && !target.isEmpty(), "target cannot be null or empty");
    return translateRequest(text, null, source, target);
  }