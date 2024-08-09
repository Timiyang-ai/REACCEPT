static TagKeyLong create(String name) {
      checkArgument(isValid(name));
      return new AutoValue_TagKey_TagKeyLong(name);
    }