  @ParameterizedTest
  @MethodSource("data_adjust")
  public void test_adjust(int months, LocalDate date, LocalDate expected) {
    TenorAdjustment test = TenorAdjustment.of(Tenor.ofMonths(months), LAST_DAY, BDA_FOLLOW_SAT_SUN);
    assertThat(test.adjust(date, REF_DATA)).isEqualTo(expected);
    assertThat(test.resolve(REF_DATA).adjust(date)).isEqualTo(expected);
  }