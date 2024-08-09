public static TagKey create(String key) {
    return new AutoValue_TagKey(StringUtil.sanitize(key));
  }