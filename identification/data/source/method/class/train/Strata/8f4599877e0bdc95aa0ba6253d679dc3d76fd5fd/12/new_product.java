@SuppressWarnings("unchecked")
  public MarketDataRequirements requirements(ReferenceData refData, MarketDataFeed feed) {
    // determine market data requirements of the function
    FunctionRequirements functionRequirements = function.requirements(target, getMeasures(), parameters, refData);

    // convert function requirements to market data requirements
    MarketDataRequirementsBuilder requirementsBuilder = MarketDataRequirements.builder();
    for (ObservableId id : functionRequirements.getTimeSeriesRequirements()) {
      requirementsBuilder.addTimeSeries(id.withMarketDataFeed(feed));
    }
    for (MarketDataId<?> id : functionRequirements.getSingleValueRequirements()) {
      if (id instanceof ObservableId) {
        requirementsBuilder.addValues(((ObservableId) id).withMarketDataFeed(feed));
      } else {
        requirementsBuilder.addValues(id);
      }
    }

    // add requirements for the FX rates needed to convert the output values into the reporting currency
    for (CalculationTaskCell cell : cells) {
      if (cell.getMeasure().isCurrencyConvertible()) {
        Currency reportingCurrency = cell.reportingCurrency(this, refData);
        List<MarketDataId<FxRate>> fxRateIds = functionRequirements.getOutputCurrencies().stream()
            .filter(outputCurrency -> !outputCurrency.equals(reportingCurrency))
            .map(outputCurrency -> CurrencyPair.of(outputCurrency, reportingCurrency))
            .map(pair -> FxRateId.of(pair, feed))
            .collect(toImmutableList());
        requirementsBuilder.addValues(fxRateIds);
      }
    }
    return requirementsBuilder.build();
  }