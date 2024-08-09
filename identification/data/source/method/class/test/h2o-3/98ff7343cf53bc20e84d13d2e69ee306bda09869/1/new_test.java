  @Test(timeout = 10000)
  public void addNumCol() {
    double values[] = {2E19, -123.123, 0.0, 1.0, Math.exp(1), 3};

    for (double v : values)
      writer.addNumCol(0, v);

    Chunk ds = writer._nvs[0].compress();

    for (int i = 0; i < values.length; i++)
      Assert.assertEquals(i+"th values differ", values[i], ds.atd(i), 0);
  }