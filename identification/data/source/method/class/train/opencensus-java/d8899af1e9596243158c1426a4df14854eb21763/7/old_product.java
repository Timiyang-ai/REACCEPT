public static Annotation fromDescriptionAndAttributes(
      String description, Map<String, AttributeValue> attributes) {
    return new Annotation(description, attributes);
  }