public static URL getResource(final Class<?> classInSamePackage, final String resourceName) {
    URL configResource = classInSamePackage.getResource(resourceName);
    assertThat(configResource).as(resourceName).isNotNull();
    return configResource;
  }