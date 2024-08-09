  @Test
  public void create_AllowTagKeyNameWithMaxLength() {
    char[] chars = new char[TagKey.MAX_LENGTH];
    Arrays.fill(chars, 'k');
    String key = new String(chars);
    assertThat(TagKey.create(key).getName()).isEqualTo(key);
  }