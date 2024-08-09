public CalculationParameters filter(CalculationTarget target, Measure measure) {
    ImmutableMap<Class<? extends CalculationParameter>, CalculationParameter> map = MapStream.of(parameters)
        .filterValues(cp -> cp.appliesTo(target, measure))
        .toMap();
    return of(map);
  }