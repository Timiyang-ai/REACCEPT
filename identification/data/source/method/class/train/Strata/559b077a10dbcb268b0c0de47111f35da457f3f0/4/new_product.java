public static CurveMetadata discountFactors(String name, DayCount dayCount) {
    return discountFactors(CurveName.of(name), dayCount);
  }