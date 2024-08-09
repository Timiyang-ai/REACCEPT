public static URL getResource(final Class<?> classInSamePackage, final String resourceName) {
    URL resource = classInSamePackage.getResource(resourceName);
    assertThat(resource).as(resourceName).isNotNull();
    return resource;
  }