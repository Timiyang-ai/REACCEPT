  @Test
  public void test_ofClasspath_absolute() throws Exception {
    ResourceLocator test = ResourceLocator.ofClasspath("/com/opengamma/strata/collect/io/TestFile.txt");
    assertThat(test.getLocator())
        .startsWith("classpath:")
        .endsWith("com/opengamma/strata/collect/io/TestFile.txt");
    assertThat(test.getByteSource().read()[0]).isEqualTo((byte) 'H');
    assertThat(test.getCharSource().readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.getCharSource(StandardCharsets.UTF_8).readLines()).isEqualTo(ImmutableList.of("HelloWorld"));
    assertThat(test.toString()).isEqualTo(test.getLocator());
  }