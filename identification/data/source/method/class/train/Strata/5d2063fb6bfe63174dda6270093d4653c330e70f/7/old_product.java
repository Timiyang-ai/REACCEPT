private <T extends Trade> ValueWithFailures<List<T>> parseFile(CsvIterator csv, Class<T> tradeType) {
    List<T> trades = new ArrayList<>();
    List<FailureItem> failures = new ArrayList<>();
    while (csv.hasNext()) {
      CsvRow row = csv.next();
      try {
        String typeRaw = row.getField(TYPE_FIELD);
        TradeInfo info = parseTradeInfo(row);
        switch (typeRaw.toUpperCase(Locale.ENGLISH)) {
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
              List<CsvRow> variableRows = new ArrayList<>();
              while (csv.hasNext() && csv.peek().getField(TYPE_FIELD).toUpperCase(Locale.ENGLISH).equals("VARIABLE")) {
                variableRows.add(csv.next());
              }
              trades.add(tradeType.cast(SwapTradeCsvLoader.parse(row, variableRows, info, resolver)));
            }
            break;
          case "TERMDEPOSIT":
          case "TERM DEPOSIT":
            if (tradeType == TermDepositTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(TermDepositTradeCsvLoader.parse(row, info, resolver)));
            }
            break;
          case "VARIABLE":
            failures.add(FailureItem.of(
                FailureReason.PARSING,
                "CSV file contained a 'Variable' type at line {} that was not preceeded by a 'Swap'",
                row.lineNumber()));
            break;
          case "FX":
          case "FXSINGLE":
          case "FX SINGLE":
            trades.add(tradeType.cast(FxSingleTradeCsvLoader.parse(row, info, resolver)));
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