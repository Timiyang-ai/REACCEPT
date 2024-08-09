@Test
public void create() {
  // simple zip files
  query(COUNT.args(_ARCHIVE_CREATE.args("X", "")), "1");
  // simple zip files
  query(COUNT.args(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args(
      "<archive:entry level='9'>X</archive:entry>", "")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args(
      "<archive:entry encoding='US-ASCII'>X</archive:entry>", "")),
      "1");
  query(COUNT.args(_ARCHIVE_CREATE.args("<archive:entry " +
      "last-modified='2000-01-01T12:12:12'>X</archive:entry>", "")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      " { }")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      " { 'format':'zip', 'algorithm':'deflate' }")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args("X", "", "<archive:options/>")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      "<archive:options><archive:format value='zip'/>" +
      "<archive:algorithm value='deflate'/></archive:options>")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      "<archive:options><archive:format value='zip'/></archive:options>")), "1");
  query(COUNT.args(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      "<archive:options><archive:format value='gzip'/></archive:options>")), "1");

  // different number of entries and contents
  error(_ARCHIVE_CREATE.args("X", "()"), Err.ARCH_DIFF);
  // name must not be empty
  error(_ARCHIVE_CREATE.args("<archive:entry/>", ""), Err.ARCH_EMPTY);
  // invalid compression level
  error(_ARCHIVE_CREATE.args("<archive:entry compression-level='x'>X</archive:entry>",
      ""), Err.ARCH_LEVEL);
  error(_ARCHIVE_CREATE.args("<archive:entry compression-level='10'>X</archive:entry>",
      ""), Err.ARCH_LEVEL);
  // invalid modification date
  error(_ARCHIVE_CREATE.args("<archive:entry last-modified='2020'>X</archive:entry>",
      ""), Err.ARCH_DATETIME);
  // content must be string or binary
  error(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", " 123"),
      Err.STRBINTYPE);
  // wrong encoding
  error(_ARCHIVE_CREATE.args("<archive:entry encoding='x'>X</archive:entry>", ""),
      Err.ARCH_ENCODING);
  // errors while converting a string
  error(_ARCHIVE_CREATE.args("<archive:entry encoding='US-ASCII'>X</archive:entry>",
      "\u00fc"), Err.ARCH_ENCODE);
  error(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      " { 'format':'rar' }"), Err.ARCH_UNKNOWN);
  // format not supported
  error(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      "<archive:options><archive:format value='rar'/></archive:options>"),
      Err.ARCH_UNKNOWN);
  // algorithm not supported
  error(_ARCHIVE_CREATE.args("<archive:entry>X</archive:entry>", "",
      "<archive:options><archive:algorithm value='unknown'/></archive:options>"),
      Err.ARCH_SUPP);
  // algorithm not supported
  error(_ARCHIVE_CREATE.args("('x','y')", "('a','b')",
      "<archive:options><archive:format value='gzip'/></archive:options>"),
      Err.ARCH_ONE);
}