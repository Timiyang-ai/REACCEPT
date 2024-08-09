  @Test
  @SuppressWarnings("deprecation")
  public void setState_IgnoresInput() {
    Stats.setState(StatsCollectionState.ENABLED);
    assertThat(Stats.getState()).isEqualTo(StatsCollectionState.DISABLED);
  }