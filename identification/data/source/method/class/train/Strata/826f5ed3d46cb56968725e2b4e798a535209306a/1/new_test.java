  @Test
  public void test_ofDaysInResetPeriod() {
    IborAveragedFixing test = IborAveragedFixing.ofDaysInResetPeriod(
        GBP_LIBOR_3M_OBS, date(2014, 7, 2), date(2014, 8, 2));
    IborAveragedFixing expected = IborAveragedFixing.builder()
        .observation(GBP_LIBOR_3M_OBS)
        .fixedRate(null)
        .weight(31)
        .build();
    assertThat(test).isEqualTo(expected);
  }