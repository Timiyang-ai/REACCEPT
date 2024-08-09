static TagKeyBoolean create(String name) {
      checkArgument(StringUtil.isValid(name));
      return new AutoValue_TagKey_TagKeyBoolean(name);
    }