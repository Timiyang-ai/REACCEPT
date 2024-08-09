  @Test
  public void test_unzip_toPath() {
    ArrayByteSource source1 = ArrayByteSource.ofUtf8("Hello World").withFileName("TestFile1.txt");
    ArrayByteSource source2 = ArrayByteSource.ofUtf8("Hello Planet").withFileName("TestFile2.txt");
    ArrayByteSource zipFile = ZipUtils.zipInMemory(ImmutableList.of(source1, source2)).withFileName("Test.foo");

    ZipUtils.unzip(zipFile, tmpDir);

    assertThat(tmpDir.resolve("TestFile1.txt")).hasContent("Hello World");
    assertThat(tmpDir.resolve("TestFile2.txt")).hasContent("Hello Planet");
  }