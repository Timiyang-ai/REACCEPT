@Test
  public void textEntry() {
    query(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1));
    query(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1, "US-ASCII"));
    error(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1, "xyz"), Err.ZIP_FAIL_X);
    // newlines are removed from the result..
    contains(_ZIP_TEXT_ENTRY.args(ZIP, ENTRY1), "aaboutab");
  }