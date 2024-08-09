@Test
  public void write() {
    // write archive and count number of entries
    final String tmp = new IOFile(sandbox(), "tmp").path();
    query(_ARCHIVE_WRITE.args(tmp, _FILE_READ_BINARY.args(ZIP)));
    query(COUNT.args(_ARCHIVE_ENTRIES.args(_FILE_READ_BINARY.args(ZIP))), "5");
    // write archive and count number of entries
    query(_ARCHIVE_WRITE.args(tmp, _FILE_READ_BINARY.args(ZIP),
        _ARCHIVE_ENTRIES.args(_FILE_READ_BINARY.args(ZIP))));
    query(COUNT.args(_ARCHIVE_ENTRIES.args(_FILE_READ_BINARY.args(ZIP))), "5");

    query("let $a := " + _ARCHIVE_ENTRIES.args(
      _FILE_READ_BINARY.args(ZIP)) + "/string() " +
      "let $f := " + _FILE_LIST.args(tmp, "true()") + '[' +
      _FILE_IS_FILE.args(" '" + tmp + "/'||.") + "] ! replace(., '\\\\', '/') " +
      "return (every $e in $a satisfies $e = $f) and (every $e in $f satisfies $e =$ a)",
      "true");
  }