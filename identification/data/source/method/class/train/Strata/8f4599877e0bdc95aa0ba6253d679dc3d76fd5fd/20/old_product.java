@SuppressWarnings("unchecked")
  public MarketDataRequirements requirements(ReferenceData refData) {
    // determine market data requirements of the function
    FunctionRequirements functionRequirements = function.requirements(target, getMeasures(), parameters, refData);

    // convert function requirements to market data requirements
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();
    functionRequirements.getTimeSeriesRequirements().stream()
        .map(marketDataMappings::getIdForObservableKey)
        .forEach(requirementsBuilder::addTimeSeries);
    for (MarketDataKey<?> key : functionRequirements.getSingleValueRequirements()) {
      requirementsBuilder.addValues(marketDataMappings.getIdForKey(key));
    }

    // add requirements for the FX rates needed to convert the output values into the reporting currency
    for (CalculationTaskCell cell : cells) {
      if (cell.getMeasure().isCurrencyConvertible()) {
        Currency reportingCurrency = cell.reportingCurrency(this, refData);
        List<MarketDataId<FxRate>> fxRateIds = functionRequirements.getOutputCurrencies().stream()
            .filter(outputCurrency -> !outputCurrency.equals(reportingCurrency))
            .map(outputCurrency -> CurrencyPair.of(outputCurrency, reportingCurrency))
            .map(FxRateKey::of)
            .map(marketDataMappings::getIdForKey)
            .collect(toImmutableList());
        requirementsBuilder.addValues(fxRateIds);
      }
    }
    return requirementsBuilder.build();
  }