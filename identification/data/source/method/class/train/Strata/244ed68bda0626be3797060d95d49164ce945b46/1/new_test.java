  @Test
  public void equals() {
    ValueAdjustment a1 = ValueAdjustment.ofReplace(200);
    ValueAdjustment a2 = ValueAdjustment.ofReplace(200);
    ValueAdjustment b = ValueAdjustment.ofDeltaMultiplier(200);
    ValueAdjustment c = ValueAdjustment.ofDeltaMultiplier(0.1);
    assertThat(a1.equals(a2)).isEqualTo(true);
    assertThat(a1.equals(b)).isEqualTo(false);
    assertThat(a1.equals(c)).isEqualTo(false);
  }