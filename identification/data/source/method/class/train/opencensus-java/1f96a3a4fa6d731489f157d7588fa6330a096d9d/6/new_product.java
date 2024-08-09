static TagKeyBoolean create(String name) {
      checkArgument(isValid(name));
      return new AutoValue_TagKey_TagKeyBoolean(name);
    }