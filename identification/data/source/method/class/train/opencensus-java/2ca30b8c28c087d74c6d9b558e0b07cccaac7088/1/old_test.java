  @Test
  public void create_AllowTagValueWithMaxLength() {
    char[] chars = new char[TagValue.MAX_LENGTH];
    Arrays.fill(chars, 'v');
    String value = new String(chars);
    assertThat(TagValue.create(value).asString()).isEqualTo(value);
  }