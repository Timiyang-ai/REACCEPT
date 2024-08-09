  @Test
  public void test_ofDayOfWeek() {
    for (DayOfWeek dow : DayOfWeek.values()) {
      RollConvention test = RollConvention.ofDayOfWeek(dow);
      assertThat(test.getName())
          .isEqualTo("Day" +
              CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert(dow.toString()).substring(0, 3));
      assertThat(test.toString())
          .isEqualTo("Day" +
              CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.UPPER_CAMEL).convert(dow.toString()).substring(0, 3));
      assertThat(RollConvention.of(test.getName())).isSameAs(test);
      assertThat(RollConvention.of("DAY" + dow.toString().substring(0, 3))).isSameAs(test);
    }
  }