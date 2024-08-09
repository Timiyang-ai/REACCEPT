@Override
  public Stream<MultiCurrencyAmount> stream() {
    return IntStream.range(0, size).mapToObj(this::get);
  }