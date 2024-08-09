public static TagKey<String> createString(String name) {
    Preconditions.checkArgument(StringUtil.isValid(name));
    return createStringInternal(name);
  }