public static Annotation fromDescriptionAndAttributes(
      String description, Map<String, AttributeValue> attributes) {
    return new AutoValue_Annotation(
        description,
        Collections.unmodifiableMap(
            new HashMap<String, AttributeValue>(checkNotNull(attributes, "attributes"))));
  }