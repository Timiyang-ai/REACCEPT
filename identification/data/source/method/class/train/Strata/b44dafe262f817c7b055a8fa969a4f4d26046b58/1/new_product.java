public CalculationParameters filter(CalculationTarget target, Measure measure) {
    ImmutableList<CalculationParameter> filtered = parameters.values().stream()
        .map(cp -> cp.filter(target, measure))
        .filter(opt -> opt.isPresent())
        .map(opt -> opt.get())
        .collect(toImmutableList());
    return of(filtered);
  }