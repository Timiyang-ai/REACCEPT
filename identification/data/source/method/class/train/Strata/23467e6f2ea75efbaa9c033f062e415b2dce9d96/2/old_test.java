  @Test
  public void test_load_oneDate_file1_date1() {
    Map<FxRateId, FxRate> map = FxRatesCsvLoader.load(DATE1, RATES_1);
    assertThat(map).hasSize(2);
    assertFile1Date1(map);
  }