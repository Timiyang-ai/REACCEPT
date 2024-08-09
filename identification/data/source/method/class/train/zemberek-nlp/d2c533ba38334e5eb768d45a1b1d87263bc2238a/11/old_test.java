  @Test
  public void fillTest() {
    LongBitVector vector = new LongBitVector(128);
    vector.add(128, false);
    for (int i = 0; i < 128; i++) {
      Assert.assertTrue(!vector.get(i));
    }
    vector.fill(true);
    for (int i = 0; i < 128; i++) {
      Assert.assertTrue(vector.get(i));
    }
    vector.fill(false);
    for (int i = 0; i < 128; i++) {
      Assert.assertTrue(!vector.get(i));
    }

    // check filling with 1 does not effect the overflow smoothnlp.core.bits of the last long.
    vector = new LongBitVector(3);
    vector.add(3, false);
    vector.fill(true);
    assertEquals(vector.getLongArray()[0], 7);

  }