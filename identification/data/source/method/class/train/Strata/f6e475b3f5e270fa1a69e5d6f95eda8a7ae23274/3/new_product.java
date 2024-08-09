public MarketDataRequirements requirements(ReferenceData refData, MarketDataFeed feed) {
    // use for loop not streams for shorter stack traces
    MarketDataRequirementsBuilder builder = MarketDataRequirements.builder();
    for (CalculationTask task : tasks) {
      builder.addRequirements(task.requirements(refData, feed));
    }
    return builder.build();
  }