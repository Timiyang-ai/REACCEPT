  @Test(expected = IllegalArgumentException.class)
  public void probabilitySampler_outOfRangeHighProbability() {
    Samplers.probabilitySampler(1.01);
  }