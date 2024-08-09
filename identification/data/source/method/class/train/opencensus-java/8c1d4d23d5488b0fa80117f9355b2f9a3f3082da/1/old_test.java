  @Test
  public void getBytes() {
    assertThat(first.getBytes()).isEqualTo(firstBytes);
    assertThat(second.getBytes()).isEqualTo(secondBytes);
  }