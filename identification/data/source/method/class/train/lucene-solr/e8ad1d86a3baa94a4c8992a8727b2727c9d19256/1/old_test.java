  @Test
  public void calcDistanceFromErrPct() {
    final SpatialContext ctx = usually() ? SpatialContext.GEO : new Geo3dSpatialContextFactory().newSpatialContext();
    final double DEP = 0.5;//distErrPct

    //the result is the diagonal distance from the center to the closest corner,
    // times distErrPct

    Shape superwide = ctx.makeRectangle(-180, 180, 0, 0);
    //0 distErrPct means 0 distance always
    assertEquals(0, SpatialArgs.calcDistanceFromErrPct(superwide, 0, ctx), 0);
    assertEquals(180 * DEP, SpatialArgs.calcDistanceFromErrPct(superwide, DEP, ctx), 0);

    Shape supertall = ctx.makeRectangle(0, 0, -90, 90);
    assertEquals(90 * DEP, SpatialArgs.calcDistanceFromErrPct(supertall, DEP, ctx), 0);

    Shape upperhalf = ctx.makeRectangle(-180, 180, 0, 90);
    assertEquals(45 * DEP, SpatialArgs.calcDistanceFromErrPct(upperhalf, DEP, ctx), 0.0001);

    Shape midCircle = ctx.makeCircle(0, 0, 45);
    assertEquals(60 * DEP, SpatialArgs.calcDistanceFromErrPct(midCircle, DEP, ctx), 0.0001);
  }