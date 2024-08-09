@Test
public void createWithOptions() {
  // non-existing DB name with options
  final String dbnameWithOptions = NAME + "DBCreateWithOptions";

  // create DB without initial content but with options
  query(_DB_CREATE.args(dbnameWithOptions, "", "", " map { 'option1': 'value1' }"));
  query(_DB_EXISTS.args(dbnameWithOptions), true);

  // create DB w/ initial content and options
  query(_DB_CREATE.args(dbnameWithOptions, "<dummy/>", "t1.xml", " map { 'option2': 'value2' }"));
  query(_DB_OPEN.args(dbnameWithOptions) + "/root()", "<dummy/>");

  // create DB w/ initial content via document constructor with options
  query(_DB_CREATE.args(dbnameWithOptions, " document { <dummy/> }", "t2.xml", " map { 'option3': 'value3' }"));
  query(_DB_OPEN.args(dbnameWithOptions) + "/root()", "<dummy/>");

  // create DB w/ initial content given as string with options
  query(_DB_CREATE.args(dbnameWithOptions, "\"<dummy/>\"", "t1.xml", " map { 'option4': 'value4' }"));
  query(_DB_OPEN.args(dbnameWithOptions) + "/root()", "<dummy/>");

  // create DB w/ initial content multiple times with options
  query(_DB_CREATE.args(dbnameWithOptions, "<dummy/>", "t1.xml", " map { 'option5': 'value5' }"));
  query(_DB_CREATE.args(dbnameWithOptions, "<dummy/>", "t1.xml", " map { 'option6': 'value6' }"));
  query(_DB_OPEN.args(dbnameWithOptions) + "/root()", "<dummy/>");

  // try to create DB twice during same query with options
  error(_DB_CREATE.args(dbnameWithOptions) + "," + _DB_CREATE.args(dbnameWithOptions, "", "", " map { 'option7': 'value7' }"), Err.BXDB_CREATE);

  // create DB from file with options
  query(_DB_CREATE.args(dbnameWithOptions, FILE, "in/", " map { 'option8': 'value8' }"));
  query(COUNT.args(COLLECTION.args(dbnameWithOptions + "/in/input.xml") + "/html"), "1");

  // create DB from folder with options
  query(_DB_CREATE.args(dbnameWithOptions, FLDR, "test/dir", " map { 'option9': 'value9' }"));
  query(COUNT.args(COLLECTION.args(dbnameWithOptions + "/test/dir")), NFLDR);

  // create DB w/ more than one input and options
  query(_DB_CREATE.args(dbnameWithOptions, "(<a/>,<b/>)", "('1.xml','2.xml')", " map { 'option10': 'value10' }"));
  query(_DB_CREATE.args(dbnameWithOptions, "(<a/>,'" + FILE + "')", "('1.xml','2.xml')", " map { 'option11': 'value11' }"));

  error(_DB_CREATE.args(dbnameWithOptions, "()", "1.xml", " map { 'option12': 'value12' }"), Err.BXDB_CREATEARGS);
  error(_DB_CREATE.args(dbnameWithOptions, "(<a/>,<b/>)", "1.xml", " map { 'option13': 'value13' }"), Err.BXDB_CREATEARGS);

  // create and drop more than one database with options
  query("for $i in 1 to 5 return " + _DB_CREATE.args(" '" + dbnameWithOptions + "' || $i", "", "", " map { 'option14': 'value14' }"));
  query("for $i in 1 to 5 return " + _DB_DROP.args(" '" + dbnameWithOptions + "' || $i"));

  error(_DB_CREATE.args(dbnameWithOptions, "", " map { 'option15': 'value15' }"), Err.WHICHRES);

  // create DB with initial EMPTY content and options
  error(_DB_CREATE.args("", "", "", " map { 'option16': 'value16' }"), Err.BXDB_NAME);

  // try to access non-existing DB (create is supposed to be called last) with options
  query(_DB_DROP.args(dbnameWithOptions));
  error(_DB_CREATE.args(dbnameWithOptions) + "," + _DB_DROP.args(dbnameWithOptions, "", "", " map { 'option17': 'value17' }"), Err.BXDB_OPEN);

  // run update on existing DB then drop it and create a new one with options
  query(_DB_CREATE.args(dbnameWithOptions, "<a/>", "a.xml", " map { 'option18': 'value18' }"));
  query("insert node <dummy/> into " + _DB_OPEN.args(dbnameWithOptions));
  query(_DB_CREATE.args(dbnameWithOptions, "<dummy/>", "t1.xml", " map { 'option19': 'value19' }") +
        ", insert node <dummy/> into " + _DB_OPEN.args(dbnameWithOptions) + "," +
        _DB_DROP.args(dbnameWithOptions));
  query(_DB_OPEN.args(dbnameWithOptions) + "/root()", "<dummy/>");

  // eventually drop database
  query(_DB_DROP.args(dbnameWithOptions));
}