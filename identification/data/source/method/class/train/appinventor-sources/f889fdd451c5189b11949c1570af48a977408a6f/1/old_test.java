  @Test
  public void testPointsFromString() {
    line.PointsFromString("[[1, 1], [-1, -1]]");
    YailList points = line.Points();
    assertEquals(2, points.size());
    assertEquals(1.0, (Double)((YailList)points.get(1)).get(1), DEG_TOL);
    assertEquals(1.0, (Double)((YailList)points.get(1)).get(2), DEG_TOL);
    assertEquals(-1.0, (Double)((YailList)points.get(2)).get(1), DEG_TOL);
    assertEquals(-1.0, (Double)((YailList)points.get(2)).get(2), DEG_TOL);
  }