  @Test
  public void getQuantiles() {
    final KllFloatsSketch sketch = new KllFloatsSketch();
    sketch.update(1);
    sketch.update(2);
    sketch.update(3);
    final float[] quantiles1 = sketch.getQuantiles(new double[] {0, 0.5, 1});
    final float[] quantiles2 = sketch.getQuantiles(3);
    assertEquals(quantiles1, quantiles2);
    assertEquals(quantiles1[0], 1f);
    assertEquals(quantiles1[1], 2f);
    assertEquals(quantiles1[2], 3f);
  }