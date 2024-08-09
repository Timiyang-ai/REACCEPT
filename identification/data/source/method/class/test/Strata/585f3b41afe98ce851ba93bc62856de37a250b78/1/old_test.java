@Test
  public void test_unpackInMemory_zip() {
    ArrayByteSource source1 = ArrayByteSource.ofUtf8("Hello World").withFileName("TestFile1.txt");
    ArrayByteSource source2 = ArrayByteSource.ofUtf8("Hello Planet").withFileName("TestFile2.txt");
    ArrayByteSource zipFile = ZipUtils.zipInMemory(ImmutableList.of(source1, source2)).withFileName("Test.zip");

    ZipUtils.unpackInMemory(zipFile, (name, extracted) -> {
      if (name.equals("TestFile1.txt")) {
        assertThat(extracted.getFileName()).hasValue("TestFile1.txt");
        assertThat(extracted.readUtf8()).isEqualTo("Hello World");
      } else if (name.equals("TestFile2.txt")) {
        assertThat(extracted.getFileName()).hasValue("TestFile2.txt");
        assertThat(extracted.readUtf8()).isEqualTo("Hello Planet");
      } else {
        fail("Unexpected file: " + name);
      }
    });
  }