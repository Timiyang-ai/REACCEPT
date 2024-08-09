  @Test
  public void equals() {
    AdjustableDate a1 = AdjustableDate.of(FRI_2014_07_11, BDA_FOLLOW_SAT_SUN);
    AdjustableDate a2 = AdjustableDate.of(FRI_2014_07_11, BDA_FOLLOW_SAT_SUN);
    AdjustableDate b = AdjustableDate.of(SAT_2014_07_12, BDA_FOLLOW_SAT_SUN);
    AdjustableDate c = AdjustableDate.of(FRI_2014_07_11, BDA_NONE);
    assertThat(a1.equals(a2)).isEqualTo(true);
    assertThat(a1.equals(b)).isEqualTo(false);
    assertThat(a1.equals(c)).isEqualTo(false);
  }