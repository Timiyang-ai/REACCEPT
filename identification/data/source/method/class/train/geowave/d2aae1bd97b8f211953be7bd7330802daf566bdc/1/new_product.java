@Override
  public BinRange[] getNormalizedRanges(final NumericData range) {
    if (range == null) {
      return new BinRange[0];
    }
    // if the range is a single value, clamp at -180, 180
    if (FloatCompareUtils.checkDoublesEqual(range.getMin(), range.getMax())) {

      return super.getNormalizedRanges(range);
    }
    // if its a range, treat values outside of (-180,180) as possible date
    // line crossing
    final double normalizedMin = getNormalizedLongitude(range.getMin());
    final double normalizedMax = getNormalizedLongitude(range.getMax());

    // If the normalized max is less than normalized min, the range
    // crosses the date line
    // also, special case min=0, max=-1 as this is used within JTS as the
    // envelope for empty geometry and we don't want empty geometry
    // interpreted as a dateline crossing
    if ((normalizedMax < normalizedMin)
        && !((FloatCompareUtils.checkDoublesEqual(normalizedMax, -1)
            && (FloatCompareUtils.checkDoublesEqual(normalizedMin, 0))))) {

      return new BinRange[] {new BinRange(-180, normalizedMax), new BinRange(normalizedMin, 180)};
    }

    return new BinRange[] {new BinRange(normalizedMin, normalizedMax)};
  }