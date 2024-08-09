@Test
  public void textEntry() {
    check(_ZIP_TEXT_ENTRY);
    query(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1));
    query(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1, "US-ASCII"));
    error(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1, "xyz"), Err.ZIP_FAIL);
    // newlines are removed from the result..
    contains(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1), "aaboutab");
  }