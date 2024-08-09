  @Test
  public void percentile() {
    Registry r = newRegistry();
    PercentileTimer t = PercentileTimer.get(r, r.createId("test"));
    checkPercentiles(t, 0);
  }