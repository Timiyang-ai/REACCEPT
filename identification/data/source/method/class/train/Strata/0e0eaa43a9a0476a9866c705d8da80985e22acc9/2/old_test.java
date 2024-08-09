  @Test
  public void isSemiAnnualRollDateTest() {
    LocalDate date0 = LocalDate.of(2013, 3, 14);
    LocalDate date1 = LocalDate.of(2013, 6, 20);
    LocalDate date2 = LocalDate.of(2013, 3, 20);
    LocalDate date3 = LocalDate.of(2013, 9, 20);
    assertThat(CdsImmDateLogic.isSemiAnnualRollDate(date0)).isFalse();
    assertThat(CdsImmDateLogic.isSemiAnnualRollDate(date1)).isFalse();
    assertThat(CdsImmDateLogic.isSemiAnnualRollDate(date2)).isTrue();
    assertThat(CdsImmDateLogic.isSemiAnnualRollDate(date3)).isTrue();
  }