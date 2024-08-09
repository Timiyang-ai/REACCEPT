  @Test public void evictAll() throws Exception {
    set("a", "a", "a");
    set("b", "b", "b");
    cache.evictAll();
    assertThat(cache.size()).isEqualTo(0);
    assertAbsent("a");
    assertAbsent("b");
  }