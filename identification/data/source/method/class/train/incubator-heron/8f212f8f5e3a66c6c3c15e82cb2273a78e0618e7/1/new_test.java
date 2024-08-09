@Test
  public void testAnchor() throws Exception {
    int nBuckets = 3;
    RotatingMap g = new RotatingMap(nBuckets);

    // Create some items
    for (int i = 0; i < 100; ++i) {
      g.create(i, 1);
    }

    // basic Anchor works
    for (int i = 0; i < 100; ++i) {
      Assert.assertEquals(g.anchor(i, 1), true);
      Assert.assertEquals(g.remove(i), true);
    }

    // layered anchoring
    List<Long> thingsAdded = new ArrayList<>();
    long firstKey = new Random().nextLong();
    g.create(1, firstKey);
    thingsAdded.add(firstKey);
    for (int j = 1; j < 100; ++j) {
      long key = new Random().nextLong();
      thingsAdded.add(key);
      Assert.assertEquals(g.anchor(1, key), false);
    }

    // xor ing works
    for (int j = 0; j < 99; ++j) {
      Assert.assertEquals(g.anchor(1, thingsAdded.get(j)), false);
    }

    Assert.assertEquals(g.anchor(1, thingsAdded.get(99)), true);
    Assert.assertEquals(g.remove(1), true);

    // Same test with some rotation
    thingsAdded.clear();
    firstKey = new Random().nextLong();
    g.create(1, firstKey);
    thingsAdded.add(firstKey);
    for (int j = 1; j < 100; ++j) {
      long key = new Random().nextLong();
      thingsAdded.add(key);
      Assert.assertEquals(g.anchor(1, key), false);
    }

    g.rotate();

    for (int j = 0; j < 99; ++j) {
      Assert.assertEquals(g.anchor(1, thingsAdded.get(j)), false);
    }

    Assert.assertEquals(g.anchor(1, thingsAdded.get(99)), true);
    Assert.assertEquals(g.remove(1), true);

    // Too much rotation
    thingsAdded.clear();
    firstKey = new Random().nextLong();
    g.create(1, firstKey);
    thingsAdded.add(firstKey);
    for (int j = 1; j < 100; ++j) {
      long key = new Random().nextLong();
      thingsAdded.add(key);
      Assert.assertEquals(g.anchor(1, key), false);
    }

    for (int i = 0; i < nBuckets; ++i) {
      g.rotate();
    }

    for (int j = 0; j < 100; ++j) {
      Assert.assertEquals(g.anchor(1, thingsAdded.get(j)), false);
    }

    Assert.assertEquals(g.remove(1), false);
  }