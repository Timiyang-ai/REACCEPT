public static TagKey create(String name) {
    checkArgument(isValid(name));
    return new AutoValue_TagKey(name);
  }