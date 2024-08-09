  @Test
  public void test_ofFile() throws Exception {
    File file = new File("src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
    ResourceLocator test = ResourceLocator.ofFile(file);
    assertThat(test.getLocator()).isEqualTo("file:src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
    assertThat(test.getByteSource().read()[0]).isEqualTo((byte) 'H');
    assertThat(test.getCharSource().readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.getCharSource(StandardCharsets.UTF_8).readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.toString()).isEqualTo("file:src/test/resources/com/opengamma/strata/collect/io/TestFile.txt");
  }