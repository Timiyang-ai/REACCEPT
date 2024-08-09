public static CurveParallelShifts absolute(double... shiftAmounts) {
    return new CurveParallelShifts(ShiftType.ABSOLUTE, DoubleArray.copyOf(shiftAmounts));
  }