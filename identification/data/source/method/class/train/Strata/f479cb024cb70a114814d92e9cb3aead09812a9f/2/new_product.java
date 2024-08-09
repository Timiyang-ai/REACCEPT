public static CurveMetadata sabrParameterByExpiry(
      CurveName name,
      DayCount dayCount,
      ValueType yType,
      List<? extends ParameterMetadata> parameterMetadata) {

    if (!yType.equals(ValueType.SABR_ALPHA) && !yType.equals(ValueType.SABR_BETA) &&
        !yType.equals(ValueType.SABR_RHO) && !yType.equals(ValueType.SABR_NU)) {
      throw new IllegalArgumentException("SABR y-value type must be SabrAlpha, SabrBeta, SabrRho or SabrNu");
    }
    return DefaultCurveMetadata.builder()
        .curveName(name)
        .xValueType(ValueType.YEAR_FRACTION)
        .yValueType(yType)
        .dayCount(dayCount)
        .parameterMetadata(parameterMetadata)
        .build();
  }