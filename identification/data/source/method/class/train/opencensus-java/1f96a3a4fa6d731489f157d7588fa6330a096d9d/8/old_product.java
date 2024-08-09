public static TagKeyString create(String name) {
      // TODO(sebright): Should we disallow an empty name?
      checkArgument(isValid(name));
      return new AutoValue_TagKey_TagKeyString(name);
    }