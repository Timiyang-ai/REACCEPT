public static TagValue create(String value) {
    Preconditions.checkArgument(isValid(value));
    return new AutoValue_TagValue(value);
  }