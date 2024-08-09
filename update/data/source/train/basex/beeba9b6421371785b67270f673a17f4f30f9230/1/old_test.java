@Test
  public void entries() {
    check(_ZIP_ENTRIES);
    query(_ZIP_ENTRIES.args(ZIP));
  }