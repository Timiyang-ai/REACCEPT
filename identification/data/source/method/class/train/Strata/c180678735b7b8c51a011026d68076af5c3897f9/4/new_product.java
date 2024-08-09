public static ParallelShiftedCurve relative(Curve curve, double shiftAmount) {
    return new ParallelShiftedCurve(curve, ShiftType.RELATIVE, shiftAmount);
  }