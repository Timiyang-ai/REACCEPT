  @Test
  public void cast() {

    assertThat(PostgresCast.cast(1)).isEqualTo("::integer");
    assertThat(PostgresCast.cast(1L)).isEqualTo("::bigint");
    assertThat(PostgresCast.cast(1.0D)).isEqualTo("::decimal");
    assertThat(PostgresCast.cast("")).isEqualTo("");
  }