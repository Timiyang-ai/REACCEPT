  @Test
  public void test_subtractFrom() {
    assertThat(TENOR_3D.subtractFrom(LocalDate.of(2014, 6, 30))).isEqualTo(LocalDate.of(2014, 6, 27));
    assertThat(TENOR_1W.subtractFrom(OffsetDateTime.of(2014, 6, 30, 0, 0, 0, 0, ZoneOffset.UTC)))
        .isEqualTo(OffsetDateTime.of(2014, 6, 23, 0, 0, 0, 0, ZoneOffset.UTC));
  }