private <T extends Trade> ValueWithFailures<List<T>> parseFile(CharSource charSource, Class<T> tradeType) {
    try (CsvIterator csv = CsvIterator.of(charSource, true)) {
      if (!csv.headers().contains(TYPE_FIELD)) {
        return ValueWithFailures.of(ImmutableList.of(),
            FailureItem.of(FailureReason.PARSING, "CSV file does not contain 'Type' header: {}", charSource));
      }
      return parseFile(csv, tradeType);

    } catch (RuntimeException ex) {
      return ValueWithFailures.of(ImmutableList.of(),
          FailureItem.of(FailureReason.PARSING, ex, "CSV file could not be parsed: {}", charSource));
    }
  }