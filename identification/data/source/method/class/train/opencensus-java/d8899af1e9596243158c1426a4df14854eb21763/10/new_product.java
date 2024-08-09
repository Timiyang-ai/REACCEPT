public static AttributeValue stringAttributeValue(String stringValue) {
    return new AttributeValue(checkNotNull(stringValue, "stringValue"), null, null);
  }