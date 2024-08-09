  @Test
  public void test_combinedWith() {
    HolidayCalendar base1 = new MockHolCal();
    HolidayCalendar base2 = HolidayCalendars.FRI_SAT;
    HolidayCalendar test = base1.combinedWith(base2);
    assertThat(test.toString()).isEqualTo("HolidayCalendar[Fri/Sat+Mock]");
    assertThat(test.getName()).isEqualTo("Fri/Sat+Mock");
    assertThat(test.equals(base1.combinedWith(base2))).isEqualTo(true);
    assertThat(test.equals(ANOTHER_TYPE)).isEqualTo(false);
    assertThat(test.equals(null)).isEqualTo(false);
    assertThat(test.hashCode()).isEqualTo(base1.combinedWith(base2).hashCode());

    assertThat(test.isHoliday(THU_2014_07_10)).isEqualTo(false);
    assertThat(test.isHoliday(FRI_2014_07_11)).isEqualTo(true);
    assertThat(test.isHoliday(SAT_2014_07_12)).isEqualTo(true);
    assertThat(test.isHoliday(SUN_2014_07_13)).isEqualTo(true);
    assertThat(test.isHoliday(MON_2014_07_14)).isEqualTo(false);
    assertThat(test.isHoliday(TUE_2014_07_15)).isEqualTo(false);
    assertThat(test.isHoliday(WED_2014_07_16)).isEqualTo(true);
    assertThat(test.isHoliday(THU_2014_07_17)).isEqualTo(false);
    assertThat(test.isHoliday(FRI_2014_07_18)).isEqualTo(true);
    assertThat(test.isHoliday(SAT_2014_07_19)).isEqualTo(true);
    assertThat(test.isHoliday(SUN_2014_07_20)).isEqualTo(true);
    assertThat(test.isHoliday(MON_2014_07_21)).isEqualTo(false);
  }