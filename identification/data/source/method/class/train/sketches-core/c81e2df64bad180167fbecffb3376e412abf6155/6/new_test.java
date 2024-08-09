  @Test
  public void merge() {
    final KllFloatsSketch sketch1 = new KllFloatsSketch();
    final KllFloatsSketch sketch2 = new KllFloatsSketch();
    final int n = 10000;
    for (int i = 0; i < n; i++) {
      sketch1.update(i);
      sketch2.update((2 * n) - i - 1);
    }

    assertEquals(sketch1.getMinValue(), 0.0f);
    assertEquals(sketch1.getMaxValue(), (float) (n - 1));

    assertEquals(sketch2.getMinValue(), (float) n);
    assertEquals(sketch2.getMaxValue(), (float) ((2 * n) - 1));

    sketch1.merge(sketch2);

    assertFalse(sketch1.isEmpty());
    assertEquals(sketch1.getN(), 2 * n);
    assertEquals(sketch1.getMinValue(), 0f);
    assertEquals(sketch1.getMaxValue(), (float) ((2 * n) - 1));
    assertEquals(sketch1.getQuantile(0.5), n, n * PMF_EPS_FOR_K_256);
  }