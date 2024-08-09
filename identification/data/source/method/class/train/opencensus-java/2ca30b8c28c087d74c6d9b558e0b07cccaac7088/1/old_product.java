public static TagValue create(String value) {
    Utils.checkArgument(isValid(value), "Invalid TagValue: " + value);
    return new AutoValue_TagValue(value);
  }