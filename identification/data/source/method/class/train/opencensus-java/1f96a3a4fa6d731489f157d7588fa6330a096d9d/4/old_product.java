public static TagKey create(String name) {
    Utils.checkArgument(isValid(name), "Invalid TagKey name: " + name);
    return new AutoValue_TagKey(name);
  }