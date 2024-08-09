public static PropertyKey fromString(String keyStr) {
    PropertyKey key = ConstantPropertyKey.fromString(keyStr);
    if (key != null) {
      return key;
    }
    key = ParameterizedPropertyKey.fromString(keyStr);
    if (key != null) {
      return key;
    }
    throw new IllegalArgumentException(
        ExceptionMessage.INVALID_CONFIGURATION_KEY.getMessage(keyStr));
  }