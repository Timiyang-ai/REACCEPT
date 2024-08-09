  @Test public void isClosed_uninitializedCache() throws Exception {
    // Create an uninitialized cache.
    cache = new DiskLruCache(fileSystem, cacheDir, appVersion, 2, Integer.MAX_VALUE, taskRunner);
    toClose.add(cache);

    assertThat(cache.isClosed()).isFalse();
    cache.close();
    assertThat(cache.isClosed()).isTrue();
  }