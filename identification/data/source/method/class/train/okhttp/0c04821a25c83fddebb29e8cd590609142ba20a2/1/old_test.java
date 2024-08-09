  @Test public void merge() {
    Settings a = new Settings();
    a.set(Settings.HEADER_TABLE_SIZE, 10000);
    a.set(Settings.MAX_HEADER_LIST_SIZE, 20000);
    a.set(Settings.INITIAL_WINDOW_SIZE, 30000);

    Settings b = new Settings();
    b.set(Settings.MAX_HEADER_LIST_SIZE, 40000);
    b.set(Settings.INITIAL_WINDOW_SIZE, 50000);
    b.set(Settings.MAX_CONCURRENT_STREAMS, 60000);

    a.merge(b);
    assertThat(a.getHeaderTableSize()).isEqualTo(10000);
    assertThat(a.getMaxHeaderListSize(-1)).isEqualTo(40000);
    assertThat(a.getInitialWindowSize()).isEqualTo(50000);
    assertThat(a.getMaxConcurrentStreams()).isEqualTo(60000);
  }