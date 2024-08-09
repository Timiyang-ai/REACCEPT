@Test
  public void testgetComparedMarker() {
    ScanMarker l;
    ScanMarker r;

    // equal plans
    l = new ScanMarker(new byte[] { 1, 2 }, INCLUSIVE);
    r = new ScanMarker(new byte[] { 1, 2 }, INCLUSIVE);
    assertFirstGreater(l, r);

    l = new ScanMarker(new byte[] { 1, 2 }, !INCLUSIVE);
    r = new ScanMarker(new byte[] { 1, 2 }, !INCLUSIVE);
    assertFirstGreater(l, r);

    l = new ScanMarker(null, !INCLUSIVE);
    r = new ScanMarker(null, !INCLUSIVE);
    assertFirstGreater(l, r);

    // create l is greater because of inclusive flag
    l = new ScanMarker(new byte[] { 1, 2 }, !INCLUSIVE);
    r = new ScanMarker(null, !INCLUSIVE);
    // the rule for null vs non-null is different
    // non-null is both smaller and greater than null
    Assert.assertEquals(l, ScanPlan.getComparedMarker(l, r, true));
    Assert.assertEquals(l, ScanPlan.getComparedMarker(r, l, true));
    Assert.assertEquals(l, ScanPlan.getComparedMarker(l, r, false));
    Assert.assertEquals(l, ScanPlan.getComparedMarker(r, l, false));

    // create l that is greater because of the bytes
    l = new ScanMarker(new byte[] { 1, 2, 0 }, INCLUSIVE);
    r = new ScanMarker(new byte[] { 1, 2 }, INCLUSIVE);
    assertFirstGreater(l, r);

  }