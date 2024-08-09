public static TagKeyString create(String name) {
      checkArgument(StringUtil.isValid(name));
      return new AutoValue_TagKey_TagKeyString(name);
    }