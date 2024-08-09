  @Test
  public void test_datePeriod() {
    assertThat(SummarizerUtils.datePeriod(LocalDate.of(2017, 10, 12), LocalDate.of(2019, 10, 12))).isEqualTo("2Y");
    assertThat(SummarizerUtils.datePeriod(LocalDate.of(2017, 10, 12), LocalDate.of(2019, 12, 12))).isEqualTo("2Y2M");
  }