@Test
  public void zipFile() throws IOException {
    // check first file
    query(_ZIP_ZIP_FILE.args(params("<entry name='one'/>")));
    checkEntry("one", new byte[0]);
    // check second file
    query(_ZIP_ZIP_FILE.args(params("<entry name='two'>!</entry>")));
    checkEntry("two", new byte[] { '!' });
    // check third file
    query(_ZIP_ZIP_FILE.args(
        params("<entry name='three' encoding='UTF-16'>!</entry>")));
    checkEntry("three", new byte[] { '\0', '!' });
    // check fourth file
    query(_ZIP_ZIP_FILE.args(params("<entry name='four' src='" + TMPFILE + "'/>")));
    checkEntry("four", new byte[] { '!' });
    // check fifth file
    query(_ZIP_ZIP_FILE.args(params("<entry src='" + TMPFILE + "'/>")));
    checkEntry(NAME + ".tmp", new byte[] { '!' });
    // check sixth file
    query(_ZIP_ZIP_FILE.args(params("<dir name='a'><entry name='b' src='" +
        TMPFILE + "'/></dir>")));
    checkEntry("a/b", new byte[] { '!' });

    // error: duplicate entry specified
    error(_ZIP_ZIP_FILE.args(params("<entry src='" + TMPFILE + "'/>" +
        "<entry src='" + TMPFILE + "'/>")), Err.ZIP_FAIL_X);
  }