public static TagKey<String> createString(String key) {
    return new AutoValue_TagKey<String>(StringUtil.sanitize(key), TAG_STRING);
  }