  @ParameterizedTest
  @MethodSource("data_nextLeapDay")
  public void test_nextLeapDay_LocalDate(int year, int month, int day, int expectedYear) {
    LocalDate date = LocalDate.of(year, month, day);
    LocalDate test = DateAdjusters.nextLeapDay().adjust(date);
    assertThat(test.getYear()).isEqualTo(expectedYear);
    assertThat(test.getMonthValue()).isEqualTo(2);
    assertThat(test.getDayOfMonth()).isEqualTo(29);
  }