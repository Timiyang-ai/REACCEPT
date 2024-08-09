  @Test
  public void test_equals_hashCode() {
    Tenor a1 = TENOR_3D;
    Tenor a2 = Tenor.ofDays(3);
    Tenor b = TENOR_4M;
    assertThat(a1.equals(a1)).isEqualTo(true);
    assertThat(a1.equals(b)).isEqualTo(false);
    assertThat(a1.equals(a2)).isEqualTo(true);

    assertThat(a2.equals(a1)).isEqualTo(true);
    assertThat(a2.equals(a2)).isEqualTo(true);
    assertThat(a2.equals(b)).isEqualTo(false);

    assertThat(b.equals(a1)).isEqualTo(false);
    assertThat(b.equals(a2)).isEqualTo(false);
    assertThat(b.equals(b)).isEqualTo(true);

    assertThat(a1.hashCode()).isEqualTo(a2.hashCode());
  }