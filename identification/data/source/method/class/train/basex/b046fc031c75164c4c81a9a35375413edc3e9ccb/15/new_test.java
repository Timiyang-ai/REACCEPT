@Test
  public void update() {
    // add a new entry
    query(_FILE_READ_BINARY.args(ZIP) + " ! " +
        _ARCHIVE_UPDATE.args(" .", "X", "X") + " ! " +
        COUNT.args(_ARCHIVE_ENTRIES.args(" .")), 6);
    // add a new entry
    query(_FILE_READ_BINARY.args(ZIP) + " ! " +
        _ARCHIVE_UPDATE.args(" .", "<archive:entry>X</archive:entry>", "X") + " ! " +
        COUNT.args(_ARCHIVE_ENTRIES.args(" .")), 6);
    query(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "X") + " ! " +
        _ARCHIVE_UPDATE.args(" .", "<archive:entry>Y</archive:entry>", "Y") + " ! " +
        _ARCHIVE_EXTRACT_TEXT.args(" ."), "X Y");
    // updates an existing entry
    query(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "X") + " ! " +
        _ARCHIVE_UPDATE.args(" .", "<archive:entry>X</archive:entry>", "Y") + " ! " +
        _ARCHIVE_EXTRACT_TEXT.args(" ."), "Y");
    // updates an existing entry
    error(_ARCHIVE_CREATE.args("X", "X",
        "<archive:options><archive:format value='gzip'/></archive:options>") + " ! " +
        _ARCHIVE_UPDATE.args(" .", "X", "Y"), Err.ARCH_MODIFY_X);
  }