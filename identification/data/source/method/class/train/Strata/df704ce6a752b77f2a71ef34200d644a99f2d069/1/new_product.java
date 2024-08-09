public PointSensitivities mapSensitivities(DoubleUnaryOperator operator) {
    return sensitivities.stream()
        .map(s -> s.withSensitivity(operator.applyAsDouble(s.getSensitivity())))
        .collect(
            Collectors.collectingAndThen(
                Guavate.toImmutableList(),
                PointSensitivities::new));
  }