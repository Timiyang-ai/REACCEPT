  @Test
  public void getNextSemiAnnualRollDateTest() {
    LocalDate[] dates = new LocalDate[] {LocalDate.of(2013, 3, 14), LocalDate.of(2013, 6, 20), LocalDate.of(2013, 3, 20),
        LocalDate.of(2013, 9, 20), LocalDate.of(2013, 1, 21),
        LocalDate.of(2013, 3, 21), LocalDate.of(2013, 9, 19), LocalDate.of(2013, 9, 21), LocalDate.of(2013, 11, 21)};
    LocalDate[] datesExp = new LocalDate[] {LocalDate.of(2013, 3, 20), LocalDate.of(2013, 9, 20), LocalDate.of(2013, 9, 20),
        LocalDate.of(2014, 3, 20), LocalDate.of(2013, 3, 20),
        LocalDate.of(2013, 9, 20), LocalDate.of(2013, 9, 20), LocalDate.of(2014, 3, 20), LocalDate.of(2014, 3, 20)};
    for (int i = 0; i < dates.length; ++i) {
      assertThat(CdsImmDateLogic.getNextSemiAnnualRollDate(dates[i])).isEqualTo(datesExp[i]);
    }
  }