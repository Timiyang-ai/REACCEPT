@Test
  public void testgetComparedMarker() {
    ScanMarker l;
    ScanMarker r;

    // equal plans
    l = new ScanMarker("1", INCLUSIVE, "int");
    r = new ScanMarker("1", INCLUSIVE, "int");
    assertFirstGreater(l, r);

    l = new ScanMarker("1", !INCLUSIVE, "int");
    r = new ScanMarker("1", !INCLUSIVE, "int");
    assertFirstGreater(l, r);

    assertFirstGreater(null, null);

    // create l is greater because of inclusive flag
    l = new ScanMarker("1", !INCLUSIVE, "int");
    // the rule for null vs non-null is different
    // non-null is both smaller and greater than null
    Assert.assertEquals(l, ScanPlan.getComparedMarker(l, null, true));
    Assert.assertEquals(l, ScanPlan.getComparedMarker(null, l, true));
    Assert.assertEquals(l, ScanPlan.getComparedMarker(l, null, false));
    Assert.assertEquals(l, ScanPlan.getComparedMarker(null, l, false));

    // create l that is greater because of the bytes
    l = new ScanMarker("2", INCLUSIVE, "int");
    r = new ScanMarker("1", INCLUSIVE, "int");
    assertFirstGreater(l, r);

  }