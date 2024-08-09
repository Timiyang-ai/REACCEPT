public static double calcDistanceFromErrPct(Shape shape, double distErrPct, SpatialContext ctx) {
    if (distErrPct < 0 || distErrPct > 0.5) {
      throw new IllegalArgumentException("distErrPct " + distErrPct + " must be between [0 to 0.5]");
    }
    if (distErrPct == 0 || shape instanceof Point) {
      return 0;
    }
    Rectangle bbox = shape.getBoundingBox();
    //The diagonal distance should be the same computed from any opposite corner,
    // and this is the longest distance that might be occurring within the shape.
    double diagonalDist = ctx.getDistCalc().distance(
        ctx.makePoint(bbox.getMinX(), bbox.getMinY()), bbox.getMaxX(), bbox.getMaxY());
    return diagonalDist * 0.5 * distErrPct;
  }