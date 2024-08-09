  @Test
  public void test_ofDayOfMonth() {
    for (int i = 1; i < 30; i++) {
      RollConvention test = RollConvention.ofDayOfMonth(i);
      assertThat(test.adjust(date(2014, JULY, 1))).isEqualTo(date(2014, JULY, i));
      assertThat(test.getName()).isEqualTo("Day" + i);
      assertThat(test.toString()).isEqualTo("Day" + i);
      assertThat(RollConvention.of(test.getName())).isSameAs(test);
      assertThat(RollConvention.of("DAY" + i)).isSameAs(test);
    }
  }