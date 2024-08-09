public static FloatingRateIndex parse(String indexStr, Tenor defaultIborTenor) {
    ArgChecker.notNull(indexStr, "indexStr");
    Optional<IborIndex> iborOpt = IborIndex.extendedEnum().find(indexStr);
    if (iborOpt.isPresent()) {
      return iborOpt.get();
    }
    Optional<OvernightIndex> overnightOpt = OvernightIndex.extendedEnum().find(indexStr);
    if (overnightOpt.isPresent()) {
      return overnightOpt.get();
    }
    Optional<PriceIndex> priceOpt = PriceIndex.extendedEnum().find(indexStr);
    if (priceOpt.isPresent()) {
      return priceOpt.get();
    }
    Optional<FloatingRateName> frnOpt = FloatingRateName.extendedEnum().find(indexStr);
    if (frnOpt.isPresent()) {
      FloatingRateName frn = frnOpt.get();
      return frn.toFloatingRateIndex(defaultIborTenor != null ? defaultIborTenor : frn.getDefaultTenor());
    }
    throw new IllegalArgumentException("Floating rate index not known: " + indexStr);
  }