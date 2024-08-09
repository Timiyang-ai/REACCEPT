  @Test
  public void longToBase16String() {
    char[] chars1 = new char[BigendianEncoding.LONG_BASE16];
    BigendianEncoding.longToBase16String(FIRST_LONG, chars1, 0);
    assertThat(chars1).isEqualTo(FIRST_CHAR_ARRAY);

    char[] chars2 = new char[BigendianEncoding.LONG_BASE16];
    BigendianEncoding.longToBase16String(SECOND_LONG, chars2, 0);
    assertThat(chars2).isEqualTo(SECOND_CHAR_ARRAY);

    char[] chars3 = new char[2 * BigendianEncoding.LONG_BASE16];
    BigendianEncoding.longToBase16String(FIRST_LONG, chars3, 0);
    BigendianEncoding.longToBase16String(SECOND_LONG, chars3, BigendianEncoding.LONG_BASE16);
    assertThat(chars3).isEqualTo(BOTH_CHAR_ARRAY);
  }