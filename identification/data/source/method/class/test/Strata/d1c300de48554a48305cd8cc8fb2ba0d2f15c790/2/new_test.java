  @Test
  public void test_ofClasspathUrl() throws Exception {
    URL url = Resources.getResource("com/opengamma/strata/collect/io/TestFile.txt");
    ResourceLocator test = ResourceLocator.ofClasspathUrl(url);
    assertThat(test.getLocator())
        .startsWith("classpath:")
        .endsWith("com/opengamma/strata/collect/io/TestFile.txt");
    assertThat(test.getByteSource().read()[0]).isEqualTo((byte) 'H');
    assertThat(test.getCharSource().readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.getCharSource(StandardCharsets.UTF_8).readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.toString()).isEqualTo(test.getLocator());
  }