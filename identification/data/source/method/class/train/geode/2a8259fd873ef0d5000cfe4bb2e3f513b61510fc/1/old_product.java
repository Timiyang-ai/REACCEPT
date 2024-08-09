public static URL getResource(final String resourceName) throws ClassNotFoundException {
    URL configResource = getCallerClass(2).getResource(resourceName);
    assertThat(configResource).as(resourceName).isNotNull();
    return configResource;
  }