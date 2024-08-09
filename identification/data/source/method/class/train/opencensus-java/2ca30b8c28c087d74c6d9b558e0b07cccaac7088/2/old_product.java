public static TagValueString create(String value) {
      Preconditions.checkArgument(isValid(value));
      return new AutoValue_TagValue_TagValueString(value);
    }