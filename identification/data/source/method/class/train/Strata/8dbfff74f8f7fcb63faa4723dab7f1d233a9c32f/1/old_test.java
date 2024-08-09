  @Test
  public void test_toHeader_withCurrency() {
    ColumnHeader test = Column.of(TestingMeasures.PRESENT_VALUE, "NPV", USD).toHeader();
    assertThat(test.getName()).isEqualTo(ColumnName.of("NPV"));
    assertThat(test.getMeasure()).isEqualTo(TestingMeasures.PRESENT_VALUE);
    assertThat(test.getCurrency()).isEqualTo(Optional.of(USD));
  }