  private BinRange[] getNormalizedRanges(final double minRange, final double maxRange) {

    return new LongitudeDefinition().getNormalizedRanges(new NumericRange(minRange, maxRange));
  }