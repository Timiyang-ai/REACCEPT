public String getColumnFromProperty(Class<?> beanClass, String propertyName) {

    return toUnderscoreFromCamel(propertyName);
  }