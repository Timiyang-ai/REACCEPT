private <T extends Trade> ValueWithFailures<List<T>> parseFile(CsvIterator csv, Class<T> tradeType) {
    List<T> trades = new ArrayList<>();
    List<FailureItem> failures = new ArrayList<>();
    for (CsvRow row : csv.asIterable()) {
      try {
        String typeRaw = row.getField(TYPE_FIELD);
        TradeInfo info = parseTradeInfo(row);
        String typeUpper = typeRaw.toUpperCase(Locale.ENGLISH);
        // allow type matching to be overridden
        Optional<Trade> overrideOpt = resolver.overrideParseTrade(typeUpper, row, info);
        if (overrideOpt.isPresent()) {
          if (tradeType.isInstance(overrideOpt.get())) {
            trades.add(tradeType.cast(overrideOpt.get()));
          }
          continue;
        }
        // standard type matching
        switch (typeUpper) {
          case "FRA":
            if (tradeType == FraTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseFraTrade(row, info)));
            }
            break;
          case "SECURITY":
            if (tradeType == SecurityTrade.class || tradeType == GenericSecurityTrade.class ||
                tradeType == ResolvableSecurityTrade.class || tradeType == Trade.class) {
              SecurityQuantityTrade parsed = resolver.parseSecurityTrade(row, info);
              if (tradeType.isInstance(parsed)) {
                trades.add(tradeType.cast(parsed));
              }
            }
            break;
          case "SWAP":
            if (tradeType == SwapTrade.class || tradeType == Trade.class) {
              List<CsvRow> variableRows = new ArrayList<>();
              while (csv.hasNext() && csv.peek().getField(TYPE_FIELD).toUpperCase(Locale.ENGLISH).equals("VARIABLE")) {
                variableRows.add(csv.next());
              }
              trades.add(tradeType.cast(resolver.parseSwapTrade(row, variableRows, info)));
            }
            break;
          case "SWAPTION":
            if (tradeType == SwaptionTrade.class || tradeType == Trade.class) {
              List<CsvRow> variableRows = new ArrayList<>();
              while (csv.hasNext() && csv.peek().getField(TYPE_FIELD).toUpperCase(Locale.ENGLISH).equals("VARIABLE")) {
                variableRows.add(csv.next());
              }
              trades.add(tradeType.cast(resolver.parseSwaptionTrade(row, variableRows, info)));
            }
            break;
          case "BULLET":
          case "BULLETPAYMENT":
          case "BULLET PAYMENT":
            if (tradeType == BulletPaymentTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseBulletPaymentTrade(row, info)));
            }
            break;
          case "TERMDEPOSIT":
          case "TERM DEPOSIT":
            if (tradeType == TermDepositTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseTermDepositTrade(row, info)));
            }
            break;
          case "VARIABLE":
            failures.add(FailureItem.of(
                FailureReason.PARSING,
                "CSV file contained a 'Variable' type at line {lineNumber} that was not preceeded by a 'Swap' or 'Swaption'",
                row.lineNumber()));
            break;
          case "FX":
          case "FXSINGLE":
          case "FX SINGLE":
            if (tradeType == FxSingleTrade.class || tradeType == FxTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseFxSingleTrade(row, info)));
            }
            break;
          case "FXSWAP":
          case "FX SWAP":
            if (tradeType == FxSwapTrade.class || tradeType == FxTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseFxSwapTrade(row, info)));
            }
            break;
          case "FXVANILLAOPTION":
          case "FX VANILLA OPTION":
            if (tradeType == FxVanillaOptionTrade.class || tradeType == FxTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseFxVanillaOptionTrade(row, info)));
            }
            break;
          case "CDS":
            if (tradeType == CdsTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseCdsTrade(row, info)));
            }
            break;
          case "CDSINDEX":
          case "CDS INDEX":
            if (tradeType == CdsIndexTrade.class || tradeType == Trade.class) {
              trades.add(tradeType.cast(resolver.parseCdsIndexTrade(row, info)));
            }
            break;
          default:
            // type is not a standard one
            Optional<Trade> parsedOpt = resolver.parseOtherTrade(typeUpper, row, info);
            if (parsedOpt.isPresent()) {
              if (tradeType.isInstance(parsedOpt.get())) {
                trades.add(tradeType.cast(parsedOpt.get()));
              }
            } else {
              failures.add(FailureItem.of(
                  FailureReason.PARSING,
                  "CSV file trade type '{tradeType}' is not known at line {lineNumber}",
                  typeRaw,
                  row.lineNumber()));
            }
            break;
        }
      } catch (RuntimeException ex) {
        failures.add(FailureItem.of(
            FailureReason.PARSING,
            ex,
            "CSV file trade could not be parsed at line {lineNumber}: {exceptionMessage}",
            row.lineNumber(),
            ex.getMessage()));
      }
    }
    return ValueWithFailures.of(trades, failures);
  }