@Test
public void zipFile() throws IOException {
  check(_ZIP_ZIP_FILE);
  // check first file
  query(_ZIP_ZIP_FILE.args(zipParams("<entry name='one'/>")));
  checkZipEntry("one", new byte[0]);
  // check second file
  query(_ZIP_ZIP_FILE.args(zipParams("<entry name='two'>!</entry>")));
  checkZipEntry("two", new byte[] { '!' });
  // check third file
  query(_ZIP_ZIP_FILE.args(
      zipParams("<entry name='three' encoding='UTF-16'>!</entry>")));
  checkZipEntry("three", new byte[] { '\0', '!' });
  // check fourth file
  query(_ZIP_ZIP_FILE.args(zipParams("<entry name='four' src='" + TMPFILE + "'/>")));
  checkZipEntry("four", new byte[] { '!' });
  // check fifth file
  query(_ZIP_ZIP_FILE.args(zipParams("<entry src='" + TMPFILE + "'/>")));
  checkZipEntry(NAME + ".tmp", new byte[] { '!' });
  // check sixth file
  query(_ZIP_ZIP_FILE.args(zipParams("<dir name='a'><entry name='b' src='" +
      TMPFILE + "'/></dir>")));
  checkZipEntry("a/b", new byte[] { '!' });

  // Adjusted error handling: Expecting an exception throw, not just a message comparison
  // error: no entry specified
  assertThrows(QueryException.class, () -> {
    query(_ZIP_ZIP_FILE.args(zipParams("")));
  }, Err.ZIP_FAIL);
  // error: duplicate entry specified
  assertThrows(QueryException.class, () -> {
    query(_ZIP_ZIP_FILE.args(zipParams("<entry src='" + TMPFILE + "'/>" +
        "<entry src='" + TMPFILE + "'/>")));
  }, Err.ZIP_FAIL);
}