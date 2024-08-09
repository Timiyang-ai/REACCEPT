@Test
  public void create() {
    // simple zip files
    count(_ARCHIVE_CREATE.args("X", ""), 1);

    // simple zip files
    count(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", ""), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry level='9'>X</archive:entry>", ""), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry encoding='US-ASCII'>X</archive:entry>", ""), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry " +
        "last-modified='2000-01-01T12:12:12'>X</archive:entry>", ""), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "", " map { }"), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "",
        " map { 'format':'zip', 'algorithm':'deflate' }"), 1);
    count(_ARCHIVE_CREATE.args("X", "", " map {}"), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "",
        " map { 'format': 'zip', 'algorithm': 'deflate' }"), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "",
        " map { 'format': 'zip' }"), 1);
    count(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "",
        " map { 'format': 'gzip' }"), 1);

    // different number of entries and contents
    error(_ARCHIVE_CREATE.args("X", " ()"), ARCHIVE_NUMBER_X_X);
    // name must not be empty
    error(_ARCHIVE_CREATE.args(" <archive:entry/>", ""), ARCHIVE_DESCRIPTOR1);
    // invalid compression level
    error(_ARCHIVE_CREATE.args(" <archive:entry compression-level='x'>X</archive:entry>", ""),
        ARCHIVE_DESCRIPTOR2_X);
    error(_ARCHIVE_CREATE.args(" <archive:entry compression-level='10'>X</archive:entry>", ""),
        ARCHIVE_DESCRIPTOR2_X);
    // invalid modification date
    error(_ARCHIVE_CREATE.args(" <archive:entry last-modified='2020'>X</archive:entry>", ""),
        ARCHIVE_DESCRIPTOR3_X);
    // content must be string or binary
    error(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", " 123"), STRBIN_X_X);
    // wrong encoding
    error(_ARCHIVE_CREATE.args(" <archive:entry encoding='x'>X</archive:entry>", ""),
        ARCHIVE_ENCODE1_X);
    // errors while converting a string
    error(_ARCHIVE_CREATE.args(" <archive:entry encoding='US-ASCII'>X</archive:entry>",
        "\u00fc"), ARCHIVE_ENCODE2_X);
    // format not supported
    error(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "", " map { 'format':'rar' }"),
        ARCHIVE_FORMAT);
    // unknown option
    error(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "", " map { 'x':'y' }"),
        INVALIDOPT_X);
    error(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "",
        " map { 'format': 'xxx' }"), ARCHIVE_FORMAT);
    // algorithm not supported
    error(_ARCHIVE_CREATE.args(" <archive:entry>X</archive:entry>", "",
        " map { 'algorithm': 'unknown' }"), ARCHIVE_FORMAT_X_X);
    // algorithm not supported
    error(_ARCHIVE_CREATE.args(" ('x','y')", " ('a','b')",
        " map { 'format': 'gzip' }"), ARCHIVE_SINGLE_X);
  }