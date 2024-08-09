  @Test
  public void test_load_security() {
    PositionCsvLoader test = PositionCsvLoader.standard();
    ValueWithFailures<List<Position>> trades = test.load(FILE);

    List<SecurityPosition> filtered = trades.getValue().stream()
        .flatMap(filtering(SecurityPosition.class))
        .collect(toImmutableList());
    assertThat(filtered).hasSize(2);

    assertBeanEquals(SECURITY1, filtered.get(0));
    assertBeanEquals(SECURITY2, filtered.get(1));
  }