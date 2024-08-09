  @Test
  public void test_toCharSource_noBomUtf8() throws IOException {
    byte[] bytes = {'H', 'e', 'l', 'l', 'o'};
    ByteSource byteSource = ByteSource.wrap(bytes);
    CharSource charSource = UnicodeBom.toCharSource(byteSource);
    String str = charSource.read();
    assertThat(str).isEqualTo("Hello");
    assertThat(charSource.asByteSource(StandardCharsets.UTF_8).contentEquals(byteSource)).isTrue();
    assertThat(charSource.toString().startsWith("UnicodeBom")).isEqualTo(true);
  }