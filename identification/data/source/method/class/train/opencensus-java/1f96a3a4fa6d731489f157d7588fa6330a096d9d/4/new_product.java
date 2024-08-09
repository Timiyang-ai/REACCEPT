public static TagKey create(String name) {
    Utils.checkArgument(isValid(name), "Invalid TagKey name: %s", name);
    return new AutoValue_TagKey(name);
  }