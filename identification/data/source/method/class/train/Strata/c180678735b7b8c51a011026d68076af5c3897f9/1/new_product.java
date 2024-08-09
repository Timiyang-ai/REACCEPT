public static ParallelShiftedCurve absolute(Curve curve, double shiftAmount) {
    return new ParallelShiftedCurve(curve, ShiftType.ABSOLUTE, shiftAmount);
  }