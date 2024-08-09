public static FloatingRateIndex parse(String indexStr, Tenor defaultIborTenor) {
    ArgChecker.notNull(indexStr, "indexStr");
    return tryParse(indexStr, defaultIborTenor)
        .orElseThrow(() -> new IllegalArgumentException("Floating rate index not known: " + indexStr));
  }