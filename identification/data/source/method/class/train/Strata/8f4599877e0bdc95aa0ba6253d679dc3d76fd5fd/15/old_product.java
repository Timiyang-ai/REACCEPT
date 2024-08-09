public MarketDataRequirements requirements() {
    CalculationRequirements calculationRequirements = function.requirements(target);
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();

    calculationRequirements.getTimeSeriesRequirements().stream()
        .map(marketDataMappings::getIdForObservableKey)
        .forEach(requirementsBuilder::addTimeSeries);

    for (MarketDataKey<?> key : calculationRequirements.getSingleValueRequirements()) {
      requirementsBuilder.addValues(marketDataMappings.getIdForKey(key));
    }
    Optional<Currency> optionalReportingCurrency =
        reportingCurrency(reportingRules.reportingCurrency(target), function.defaultReportingCurrency(target));

    if (optionalReportingCurrency.isPresent()) {
      Currency reportingCurrency = optionalReportingCurrency.get();

      // Add requirements for the FX rates needed to convert the output values into the reporting currency
      List<MarketDataId<FxRate>> fxRateIds = calculationRequirements.getOutputCurrencies().stream()
          .filter(outputCurrency -> !outputCurrency.equals(reportingCurrency))
          .map(outputCurrency -> CurrencyPair.of(outputCurrency, reportingCurrency))
          .map(FxRateKey::of)
          .map(marketDataMappings::getIdForKey)
          .collect(toImmutableList());
      requirementsBuilder.addValues(fxRateIds);
    }
    return requirementsBuilder.build();
  }