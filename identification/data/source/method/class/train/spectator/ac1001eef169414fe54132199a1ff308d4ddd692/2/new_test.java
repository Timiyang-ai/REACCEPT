  @Test
  public void percentile() {
    Registry r = newRegistry();
    PercentileDistributionSummary t = PercentileDistributionSummary.get(r, r.createId("test"));
    checkPercentiles(t, 0);
  }