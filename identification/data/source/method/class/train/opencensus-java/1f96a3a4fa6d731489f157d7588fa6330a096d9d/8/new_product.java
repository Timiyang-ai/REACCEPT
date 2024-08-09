public static TagKey create(String name) {
    // TODO(sebright): Should we disallow an empty name?
    checkArgument(isValid(name));
    return new AutoValue_TagKey(name);
  }