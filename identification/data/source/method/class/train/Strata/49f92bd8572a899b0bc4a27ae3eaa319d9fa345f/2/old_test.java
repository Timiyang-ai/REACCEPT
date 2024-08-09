  @Test
  public void test_relativeTime() {
    double test1 = VOLS.relativeTime(VAL_DATE_TIME);
    assertThat(test1).isEqualTo(0d);
    double test2 = VOLS.relativeTime(date(2018, 2, 17).atStartOfDay(LONDON_ZONE));
    double test3 = VOLS.relativeTime(date(2012, 2, 17).atStartOfDay(LONDON_ZONE));
    assertThat(test2).isEqualTo(-test3); // consistency checked
  }