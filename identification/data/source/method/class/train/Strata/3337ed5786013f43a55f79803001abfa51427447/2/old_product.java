public CurveSensitivities withParameterMetadatas(Function<List<ParameterMetadata>, List<ParameterMetadata>> nameFn) {
    return new CurveSensitivities(
        info,
        MapStream.of(typedSensitivities)
            .mapValues(sens -> CurrencyParameterSensitivities.of(
                sens.getSensitivities().stream()
                    .map(single -> single.toBuilder()
                        .parameterMetadata(nameFn.apply(single.getParameterMetadata()))
                        .build())
                    .collect(toImmutableList())))
            .toMap());
  }