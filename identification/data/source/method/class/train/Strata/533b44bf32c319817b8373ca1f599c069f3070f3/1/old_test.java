  @Test
  public void test_from_ByteSource() {
    ByteSource source = ByteSource.wrap(new byte[] {1, 2, 3});
    ArrayByteSource test = ArrayByteSource.from(source);
    assertThat(test.size()).isEqualTo(3);
    assertThat(test.getFileName()).isEmpty();
    assertThat(test.read()[0]).isEqualTo((byte) 1);
    assertThat(test.read()[1]).isEqualTo((byte) 2);
    assertThat(test.read()[2]).isEqualTo((byte) 3);
  }