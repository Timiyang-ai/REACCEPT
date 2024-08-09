  @Test
  public void putIfAbsent_absent() {
    createCacheManager();

    cache = createCache();

    assertThat(cache.putIfAbsent(1, "a")).isNull();

    assertThat(cache.get(1)).isEqualTo("a");

    changesOf(1, 1, 1, 0);
  }