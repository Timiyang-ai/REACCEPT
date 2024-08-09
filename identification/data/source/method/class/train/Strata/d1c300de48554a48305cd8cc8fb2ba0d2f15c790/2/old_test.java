  @Test
  public void test_ofUrl() throws Exception {
    File file = new File("src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
    URL url = file.toURI().toURL();
    ResourceLocator test = ResourceLocator.ofUrl(url);
    assertThat(test.getLocator())
        .startsWith("url:file:")
        .endsWith("src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
    assertThat(test.getByteSource().read()[0]).isEqualTo((byte) 'H');
    assertThat(test.getCharSource().readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.getCharSource(StandardCharsets.UTF_8).readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.toString()).isEqualTo(test.getLocator());
  }