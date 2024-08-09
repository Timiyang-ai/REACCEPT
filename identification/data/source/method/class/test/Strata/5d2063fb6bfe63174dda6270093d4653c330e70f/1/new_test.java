  @SafeVarargs
  private final <T extends Trade & Bean> void checkRoundtrip(
      Class<T> type,
      List<T> loadedTrades,
      T... expectedTrades) {

    StringBuilder buf = new StringBuilder(1024);
    TradeCsvWriter.standard().write(loadedTrades, buf);
    List<CharSource> writtenCsv = ImmutableList.of(CharSource.wrap(buf.toString()));
    ValueWithFailures<List<T>> roundtrip = TradeCsvLoader.standard().parse(writtenCsv, type);
    assertThat(roundtrip.getFailures().size()).as(roundtrip.getFailures().toString()).isEqualTo(0);
    List<T> roundtripTrades = roundtrip.getValue();
    assertThat(roundtripTrades).hasSize(expectedTrades.length);
    for (int i = 0; i < roundtripTrades.size(); i++) {
      assertBeanEquals(expectedTrades[i], roundtripTrades.get(i));
    }
  }