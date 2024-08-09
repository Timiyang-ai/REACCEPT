@Test
public void create() throws BaseXException {
  new Close().execute(context);

  // create DB without initial content
  query(_DB_CREATE.args(NAME));
  query(_DB_EXISTS.args(NAME), true);

  // Adjusted to reflect new handling of inputs and paths
  // create DB w/ initial content
  query(_DB_CREATE.args(NAME, "<dummy/>", "t1.xml"));
  query(_DB_OPEN.args(NAME) + "/root()", "<dummy/>");

  // Adjusted to reflect new handling of inputs and paths
  // create DB w/ initial content via document constructor
  query(_DB_CREATE.args(NAME, " document { <dummy/> }", "t2.xml"));
  query(_DB_OPEN.args(NAME) + "/root()", "<dummy/>");

  // Adjusted for new constructor signature reflecting multiple inputs
  // create DB w/ more than one input
  query(_DB_CREATE.args(NAME, "(<a/>,<b/>)", "('1.xml','2.xml')"));
  query(_DB_CREATE.args(NAME, "(<a/>,'" + FILE + "')", "('1.xml','2.xml')"));

  // Error scenarios adjusted for new inputs handling
  error(_DB_CREATE.args(NAME, "()", "1.xml"), Err.BXDB_CREATEARGS);
  error(_DB_CREATE.args(NAME, "(<a/>,<b/>)", "1.xml"), Err.BXDB_CREATEARGS);

  // Reflecting changes in how DB creation with options is handled
  for(final boolean b : new boolean[] { false, true }) {
    query(_DB_CREATE.args(NAME, "()", "()", " map { 'updindex':" + b + "() }"));
    query(_DB_INFO.args(NAME) + "//updindex/text()", b);
  }

  // Additional tests to ensure compatibility with new DBCreate signature
  final String[] nopt = { "maxcats", "maxlen", "indexsplitsize", "ftindexsplitsize" };
  for(final String k : nopt) {
    query(_DB_CREATE.args(NAME, "()", "()", " map { '" + k + "':1 }"));
  }
  final String[] bopt = { "textindex", "attrindex", "ftindex", "stemming",
      "casesens", "diacritics" };
  for(final String k : bopt) {
    for(final boolean v : new boolean[] { true, false }) {
      query(_DB_CREATE.args(NAME, "()", "()", " map { '" + k + "':" + v + "() }"));
    }
  }
  final String[] sopt = { "language", "stopwords" };
  for(final String k : sopt) {
    query(_DB_CREATE.args(NAME, "()", "()", " map { '" + k + "':'' }"));
  }

  // Error handling adjusted to reflect new options handling
  error(_DB_CREATE.args(NAME, "()", "()", " map { 'xyz':'abc' }"), Err.BASX_OPTIONS);
  error(_DB_CREATE.args(NAME, "()", "()", " map { 'maxlen':-1 }"), Err.BASX_VALUE);
  error(_DB_CREATE.args(NAME, "()", "()", " map { 'maxlen':'a' }"), Err.BASX_VALUE);

  // eventually drop database
  query(_DB_DROP.args(NAME));
}