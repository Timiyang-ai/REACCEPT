public static boolean isPassword(String category, String propertyName) {
    return ConfigurationPropertyType.PASSWORD.equals(getConfigurationPropertyType(category, propertyName));
  }