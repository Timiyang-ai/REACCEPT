public static CurveParallelShifts relative(double... shiftAmounts) {
    return new CurveParallelShifts(ShiftType.RELATIVE, shiftAmounts);
  }