public MarketDataRequirements requirements(ReferenceData refData) {
    // use for loop not streams for shorter stack traces
    MarketDataRequirementsBuilder builder = MarketDataRequirements.builder();
    for (CalculationTask task : calculationTasks) {
      builder.addRequirements(task.requirements(refData));
    }
    return builder.build();
  }