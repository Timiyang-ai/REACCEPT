  @Test
  public void test_load_oneDate_file1_date1() {
    Map<QuoteId, Double> map = QuotesCsvLoader.load(DATE1, QUOTES_1);
    assertThat(map).hasSize(2);
    assertFile1Date1(map);
  }