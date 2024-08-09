  @Test
  public void test_getValue() {
    CsvRow row = new CsvRow(HEADERS, SEARCH_HEADERS, 1, FIELDS);
    assertThat(row.getValue("A")).isEqualTo("m");
    assertThatIllegalArgumentException().isThrownBy(() -> row.getValue("B"));
    assertThatIllegalArgumentException().isThrownBy(() -> row.getValue("X"));

    assertThat(row.getValue("C", Period::parse)).isEqualTo(Period.ofDays(1));
    assertThatIllegalArgumentException().isThrownBy(() -> row.getValue("C", Integer::parseInt));
    assertThatIllegalArgumentException().isThrownBy(() -> row.getValue("X", Period::parse));
  }