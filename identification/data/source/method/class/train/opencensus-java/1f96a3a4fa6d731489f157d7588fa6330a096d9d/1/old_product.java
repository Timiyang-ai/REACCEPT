static TagKeyLong create(String name) {
      checkArgument(StringUtil.isValid(name));
      return new AutoValue_TagKey_TagKeyLong(name);
    }