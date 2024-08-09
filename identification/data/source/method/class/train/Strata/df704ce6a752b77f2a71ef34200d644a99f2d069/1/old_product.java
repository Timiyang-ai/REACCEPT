public PointSensitivities mapSensitivities(DoubleUnaryOperator operator) {
    return sensitivities.stream()
        .map(cs -> cs.withSensitivity(operator.applyAsDouble(cs.getSensitivity())))
        .collect(
            Collectors.collectingAndThen(
                Guavate.toImmutableList(),
                PointSensitivities::new));
  }