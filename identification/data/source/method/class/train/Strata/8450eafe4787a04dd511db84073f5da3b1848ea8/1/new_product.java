public default BoundCurveInterpolator bind(
      DoubleArray xValues,
      DoubleArray yValues,
      CurveExtrapolator extrapolatorLeft,
      CurveExtrapolator extrapolatorRight) {

    // interpolators depend on extrapolators and vice versa
    // this makes it hard to satisfy the Java Memory Model for immutability
    // handle this by creating an interpolator instance that cannot extrapolate
    // use that interpolator to bind the extrapolators
    // finally, create the bound interpolator for the caller
    BoundCurveInterpolator interpolatorOnly = bind(xValues, yValues);
    BoundCurveExtrapolator boundLeft = extrapolatorLeft.bind(xValues, yValues, interpolatorOnly);
    BoundCurveExtrapolator boundRight = extrapolatorRight.bind(xValues, yValues, interpolatorOnly);
    return interpolatorOnly.bind(boundLeft, boundRight);
  }