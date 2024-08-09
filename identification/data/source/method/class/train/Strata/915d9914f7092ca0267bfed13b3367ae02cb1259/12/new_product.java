private NodalCurve checkNodal(Curve curve) {
    ArgChecker.isTrue(curve instanceof NodalCurve, "Curve must be a NodalCurve");
    return (NodalCurve) curve;
  }