public static TagValue create(String value) {
    return new AutoValue_TagValue(StringUtil.sanitize(value));
  }