public static URL getResource(final String resourceName) {
    URL resource = getCallerClass(2).getResource(resourceName);
    assertThat(resource).as(resourceName).isNotNull();
    return resource;
  }