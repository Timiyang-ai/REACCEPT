  @Test
  public void test_addTo() {
    assertThat(TENOR_3D.addTo(LocalDate.of(2014, 6, 30))).isEqualTo(LocalDate.of(2014, 7, 3));
    assertThat(TENOR_1W.addTo(OffsetDateTime.of(2014, 6, 30, 0, 0, 0, 0, ZoneOffset.UTC)))
        .isEqualTo(OffsetDateTime.of(2014, 7, 7, 0, 0, 0, 0, ZoneOffset.UTC));
  }