public static AttributeValue stringAttributeValue(String stringValue) {
    return new AutoValue_AttributeValue(checkNotNull(stringValue, "stringValue"), null, null);
  }