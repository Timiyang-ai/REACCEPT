public static Curve absolute(Curve curve, double shiftAmount) {
    return new ParallelShiftedCurve(curve, ValueAdjustment.ofDeltaAmount(shiftAmount));
  }