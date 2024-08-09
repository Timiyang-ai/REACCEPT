public static double calcDistanceFromErrPct(Shape shape, double distErrPct, SpatialContext ctx) {
    if (distErrPct < 0 || distErrPct > 0.5) {
      throw new IllegalArgumentException("distErrPct " + distErrPct + " must be between [0 to 0.5]");
    }
    if (distErrPct == 0 || shape instanceof Point) {
      return 0;
    }
    Rectangle bbox = shape.getBoundingBox();
    //Compute the distance from the center to a corner.  Because the distance
    // to a bottom corner vs a top corner can vary in a geospatial scenario,
    // take the closest one (greater precision).
    Point ctr = bbox.getCenter();
    double y = (ctr.getY() >= 0 ? bbox.getMaxY() : bbox.getMinY());
    double diagonalDist = ctx.getDistCalc().distance(ctr, bbox.getMaxX(), y);
    return diagonalDist * distErrPct;
  }