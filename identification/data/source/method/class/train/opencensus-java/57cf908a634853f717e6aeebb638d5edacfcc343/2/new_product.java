public static TagKey<String> createStringKey(String name) {
    Preconditions.checkArgument(StringUtil.isValid(name));
    return createStringKeyInternal(name);
  }