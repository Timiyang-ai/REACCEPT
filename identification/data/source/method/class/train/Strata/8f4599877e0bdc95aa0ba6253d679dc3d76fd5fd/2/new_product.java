@SuppressWarnings("unchecked")
  public MarketDataRequirements requirements() {
    ImmutableSet<Measure> measures = ImmutableSet.of(getMeasure());
    FunctionRequirements functionRequirements = function.requirements(target, measures);

    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();

    functionRequirements.getTimeSeriesRequirements().stream()
        .map(marketDataMappings::getIdForObservableKey)
        .forEach(requirementsBuilder::addTimeSeries);

    for (MarketDataKey<?> key : functionRequirements.getSingleValueRequirements()) {
      requirementsBuilder.addValues(marketDataMappings.getIdForKey(key));
    }
    Optional<Currency> optionalReportingCurrency = reportingCurrency();
    if (optionalReportingCurrency.isPresent()) {
      Currency reportingCurrency = optionalReportingCurrency.get();

      // Add requirements for the FX rates needed to convert the output values into the reporting currency
      List<MarketDataId<FxRate>> fxRateIds = functionRequirements.getOutputCurrencies().stream()
          .filter(outputCurrency -> !outputCurrency.equals(reportingCurrency))
          .map(outputCurrency -> CurrencyPair.of(outputCurrency, reportingCurrency))
          .map(FxRateKey::of)
          .map(marketDataMappings::getIdForKey)
          .collect(toImmutableList());
      requirementsBuilder.addValues(fxRateIds);
    }
    return requirementsBuilder.build();
  }