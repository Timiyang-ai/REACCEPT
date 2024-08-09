  @Test
  public void load() {
    loadSupplier = () -> -1;
    assertThat(jcacheLoading.get(1), is(-1));
  }