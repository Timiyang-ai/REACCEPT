public static Curve relative(Curve curve, double shiftAmount) {
    return new ParallelShiftedCurve(curve, ValueAdjustment.ofMultiplier(1 + shiftAmount));
  }