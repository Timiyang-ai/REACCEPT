  @Test
  public void test_nextSameOrLastInMonth_range() {
    assertThat(HOLCAL_MON_WED.nextSameOrLastInMonth(date(2010, 1, 1))).isEqualTo(date(2010, 1, 1));
    assertThatIllegalArgumentException().isThrownBy(() -> HOLCAL_MON_WED.nextSameOrLastInMonth(LocalDate.MIN));
    assertThatIllegalArgumentException().isThrownBy(() -> HOLCAL_MON_WED.nextSameOrLastInMonth(LocalDate.MAX));
  }