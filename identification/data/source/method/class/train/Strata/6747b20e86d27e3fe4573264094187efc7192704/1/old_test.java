  @Test
  public void test_ofPath() throws Exception {
    Path path = Paths.get("src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
    ResourceLocator test = ResourceLocator.ofPath(path);
    assertThat(test.getLocator().replace('\\', '/'))
        .isEqualTo("file:src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
    assertThat(test.getByteSource().read()[0]).isEqualTo((byte) 'H');
    assertThat(test.getCharSource().readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.getCharSource(StandardCharsets.UTF_8).readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.toString().replace('\\', '/'))
        .isEqualTo("file:src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
  }