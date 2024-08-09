public String getValue(Pattern headerPattern) {
    String value = getField(headerPattern);
    if (value.isEmpty()) {
      throw new IllegalArgumentException("No value was found for header pattern: '" + headerPattern + "'");
    }
    return value;
  }