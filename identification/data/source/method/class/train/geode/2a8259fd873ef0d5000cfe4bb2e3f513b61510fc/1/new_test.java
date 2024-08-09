  @Test
  public void getResource() throws Exception {
    URL resource = ResourceUtils.getResource(resourceName);

    assertThat(resource).isNotNull();
    assertThat(IOUtils.toString(resource, Charset.defaultCharset())).contains(RESOURCE_CONTENT);
  }