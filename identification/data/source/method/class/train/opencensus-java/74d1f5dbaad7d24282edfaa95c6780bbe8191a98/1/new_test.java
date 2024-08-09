  @Test
  public void toMillis() {
    assertThat(Duration.create(10, 0).toMillis()).isEqualTo(10000L);
    assertThat(Duration.create(10, 1000).toMillis()).isEqualTo(10000L);
    assertThat(Duration.create(0, (int) 1e6).toMillis()).isEqualTo(1L);
    assertThat(Duration.create(0, 0).toMillis()).isEqualTo(0L);
    assertThat(Duration.create(-10, 0).toMillis()).isEqualTo(-10000L);
    assertThat(Duration.create(-10, -1000).toMillis()).isEqualTo(-10000L);
    assertThat(Duration.create(0, -(int) 1e6).toMillis()).isEqualTo(-1L);
  }