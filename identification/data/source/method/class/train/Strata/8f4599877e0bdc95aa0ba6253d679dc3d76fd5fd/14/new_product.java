public MarketDataRequirements requirements() {
    CalculationRequirements calculationRequirements = function.requirements(target);
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();

    calculationRequirements.getTimeSeriesRequirements().stream()
        .map(marketDataMappings::getIdForObservableKey)
        .forEach(requirementsBuilder::addTimeSeries);

    for (MarketDataKey<?> key : calculationRequirements.getSingleValueRequirements()) {
      requirementsBuilder.addValues(marketDataMappings.getIdForKey(key));
    }
    Optional<Currency> optionalReportingCurrency = reportingRules.reportingCurrency(target);

    if (optionalReportingCurrency.isPresent()) {
      Currency reportingCurrency = optionalReportingCurrency.get();

      // Add requirements for the FX rates needed to convert the output values into the reporting currency
      // TODO This assumes there is a conventional pair for the combination.
      //   If the pair has been created on the fly that isn't necessarily the case.
      //   We need a consistent way across the whole system to construct pairs that have no market convention.
      //   This means the pair created here will be the same pair used in the conversion code.
      // TODO Change this when #283 is addressed. Hopefully simplify and don't worry about market convention pairs.
      List<MarketDataId<Double>> fxRateIds = calculationRequirements.getOutputCurrencies().stream()
          .map(outputCurrency -> CurrencyPair.of(outputCurrency, reportingCurrency))
          .map(pair -> pair.isConventional() ? pair : pair.inverse())
          .map(FxRateKey::of)
          .map(marketDataMappings::getIdForKey)
          .collect(toImmutableList());
      requirementsBuilder.addValues(fxRateIds);
    }
    return requirementsBuilder.build();
  }