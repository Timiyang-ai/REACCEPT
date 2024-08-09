  @Test
  public void lowerHexToUnsignedLong_downgrades128bitIdsByDroppingHighBits() {
    assertThat(lowerHexToUnsignedLong("463ac35c9f6413ad48485a3953bb6124"))
      .isEqualTo(lowerHexToUnsignedLong("48485a3953bb6124"));
  }