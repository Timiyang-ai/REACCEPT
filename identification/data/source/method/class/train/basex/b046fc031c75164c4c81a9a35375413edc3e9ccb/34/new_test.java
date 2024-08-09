@Test
  public void delete() {
    // delete single entry
    query("let $a := " + _FILE_READ_BINARY.args(ZIP) +
          "let $b := " + _ARCHIVE_DELETE.args("$a", "infos/stopWords") +
          "let $c := " + _ARCHIVE_ENTRIES.args("$b") +
          "return count($c)", 4);
    // delete all entries except for the first
    query("let $a := " + _FILE_READ_BINARY.args(ZIP) +
          "let $b := " + _ARCHIVE_ENTRIES.args("$a") +
          "let $c := " + _ARCHIVE_DELETE.args("$a", "$b[position() > 1]") +
          "return count(archive:entries($c))", "1");
    // updates an existing entry
    error(_ARCHIVE_CREATE.args("X", "X",
        "<archive:options><archive:format value='gzip'/></archive:options>") + " ! " +
        _ARCHIVE_DELETE.args(" .", "X"), Err.ARCH_MODIFY_X);
  }