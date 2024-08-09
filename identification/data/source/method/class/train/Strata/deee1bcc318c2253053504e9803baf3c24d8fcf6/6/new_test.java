  @Test
  public void test_getField_String() {
    CsvRow row = new CsvRow(HEADERS, SEARCH_HEADERS, 1, FIELDS);
    assertThat(row.getField("A")).isEqualTo("m");
    assertThat(row.getField("B")).isEqualTo("");
    assertThatIllegalArgumentException().isThrownBy(() -> row.getField("X"));

    assertThat(row.getField("C", Period::parse)).isEqualTo(Period.ofDays(1));
    assertThatIllegalArgumentException().isThrownBy(() -> row.getField("C", Integer::parseInt));
    assertThatIllegalArgumentException().isThrownBy(() -> row.getField("X", Period::parse));
  }