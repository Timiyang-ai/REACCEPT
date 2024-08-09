public float[] getQuantiles(final double[] fractions) {
    if (isEmpty()) { return null; }
    KllFloatsQuantileCalculator quant = null;
    final float[] quantiles = new float[fractions.length];
    for (int i = 0; i < fractions.length; i++) {
      final double fraction = fractions[i];
      if      (fraction == 0.0) { quantiles[i] = minValue_; }
      else if (fraction == 1.0) { quantiles[i] = maxValue_; }
      else {
        if (quant == null) {
          quant = getQuantileCalculator();
        }
        quantiles[i] = quant.getQuantile(fraction);
      }
    }
    return quantiles;
  }