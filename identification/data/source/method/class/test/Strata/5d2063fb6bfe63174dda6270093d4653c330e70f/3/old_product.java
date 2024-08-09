private <T extends Trade> ValueWithFailures<List<T>> parseFile(CsvIterator csv, Class<T> tradeType) {
    List<T> trades = new ArrayList<>();
    List<FailureItem> failures = new ArrayList<>();
    for (CsvRow row : (Iterable<CsvRow>) () -> csv) {
      try {
        String typeRaw = row.getField(TYPE_FIELD);
        String type = typeRaw.toUpperCase(Locale.ENGLISH);
        TradeInfo info = parseTradeInfo(row);
        switch (type.toUpperCase(Locale.ENGLISH)) {
          case "FRA":
            if (tradeType == FraTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(FraTradeCsvLoader.parse(row, info, resolver)));
            }
            break;
          case "SECURITY":
            if (tradeType == SecurityTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(SecurityCsvLoader.parseTrade(row, info, resolver)));
            }
            break;
          case "SWAP":
            if (tradeType == SwapTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(SwapTradeCsvLoader.parse(row, info, resolver)));
            }
            break;
          case "TERMDEPOSIT":
          case "TERM DEPOSIT":
            if (tradeType == TermDepositTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(TermDepositTradeCsvLoader.parse(row, info, resolver)));
            }
            break;
          default:
            failures.add(FailureItem.of(
                FailureReason.PARSING, "CSV file trade type '{}' is not known at line {}", typeRaw, row.lineNumber()));
            break;
        }
      } catch (RuntimeException ex) {
        failures.add(FailureItem.of(
            FailureReason.PARSING, ex, "CSV file trade could not be parsed at line {}: " + ex.getMessage(), row.lineNumber()));
      }
    }
    return ValueWithFailures.of(trades, failures);
  }