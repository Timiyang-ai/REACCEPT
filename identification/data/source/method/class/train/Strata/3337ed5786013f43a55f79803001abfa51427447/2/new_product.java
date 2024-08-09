public CurveSensitivities withParameterMetadatas(UnaryOperator<ParameterMetadata> mdFn) {
    return new CurveSensitivities(
        info,
        MapStream.of(typedSensitivities)
            .mapValues(sens -> sens.withParameterMetadatas(mdFn))
            .toMap());
  }