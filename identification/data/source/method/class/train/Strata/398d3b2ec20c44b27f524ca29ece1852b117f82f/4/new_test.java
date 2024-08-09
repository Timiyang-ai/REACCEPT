  @Test
  public void test_parse_String_roundTrip() {
    assertThat(Tenor.parse(TENOR_10M.toString())).isEqualTo(TENOR_10M);
  }