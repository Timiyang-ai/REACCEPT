@Test
  public void create() throws BaseXException {
    new Close().execute(context);

    // create DB without initial content
    query(_DB_CREATE.args(NAME));
    query(_DB_EXISTS.args(NAME), true);

    // create DB w/ initial content
    query(_DB_CREATE.args(NAME, "<dummy/>", "t1.xml"));
    query(_DB_OPEN.args(NAME) + "/root()", "<dummy/>");

    // create DB w/ initial content via document constructor
    query(_DB_CREATE.args(NAME, " document { <dummy/> }", "t2.xml"));
    query(_DB_OPEN.args(NAME) + "/root()", "<dummy/>");

    // create DB w/ initial content given as string
    query(_DB_CREATE.args(NAME, "\"<dummy/>\"", "t1.xml"));
    query(_DB_OPEN.args(NAME) + "/root()", "<dummy/>");

    // create DB w/ initial content multiple times
    query(_DB_CREATE.args(NAME, "<dummy/>", "t1.xml"));
    query(_DB_CREATE.args(NAME, "<dummy/>", "t1.xml"));
    query(_DB_OPEN.args(NAME) + "/root()", "<dummy/>");

    // try to create DB twice during same query
    error(_DB_CREATE.args(NAME) + ',' + _DB_CREATE.args(NAME), BXDB_ONCE_X_X);

    // create DB from file
    query(_DB_CREATE.args(NAME, FILE, "in/"));
    query(COUNT.args(COLLECTION.args(NAME + "/in/input.xml") + "/html"), "1");

    // create DB from folder
    query(_DB_CREATE.args(NAME, FLDR, "test/dir"));
    query(COUNT.args(COLLECTION.args(NAME + "/test/dir")), NFLDR);

    // create DB w/ more than one input
    query(_DB_CREATE.args(NAME, "(<a/>,<b/>)", "('1.xml','2.xml')"));
    query(_DB_CREATE.args(NAME, "(<a/>,'" + FILE + "')", "('1.xml','2.xml')"));

    error(_DB_CREATE.args(NAME, "()", "1.xml"), BXDB_CREATEARGS_X_X);
    error(_DB_CREATE.args(NAME, "(<a/>,<b/>)", "1.xml"), BXDB_CREATEARGS_X_X);

    // create and drop more than one database
    query("for $i in 1 to 5 return " + _DB_CREATE.args(" '" + NAME + "' || $i"));
    query("for $i in 1 to 5 return " + _DB_DROP.args(" '" + NAME + "' || $i"));

    // create DB with initial EMPTY content
    error(_DB_CREATE.args(""), BXDB_NAME_X);

    // try to access non-existing DB
    query(_DB_DROP.args(NAME));
    error(_DB_CREATE.args(NAME) + ',' + _DB_DROP.args(NAME), BXDB_WHICH_X);

    // run update on existing DB then drop it and create a new one
    query(_DB_CREATE.args(NAME, "<a/>", "a.xml"));
    query("insert node <dummy/> into " + _DB_OPEN.args(NAME));
    query(_DB_CREATE.args(NAME, "<dummy/>", "t1.xml") +
        ", insert node <dummy/> into " + _DB_OPEN.args(NAME) + ',' +
        _DB_DROP.args(NAME));
    query(_DB_OPEN.args(NAME) + "/root()", "<dummy/>");

    // eventually drop database
    query(_DB_DROP.args(NAME));

    // specify index options
    for(final boolean b : new boolean[] { false, true }) {
      query(_DB_CREATE.args(NAME, "()", "()", " map { 'updindex':" + b + "() }"));
      query(_DB_INFO.args(NAME) + "//updindex/text()", b);
    }
    assertEquals(context.options.get(MainOptions.UPDINDEX), false);

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

    // specify parsing options
    query(_DB_CREATE.args(NAME, " '<a> </a>'", "a.xml", " map { 'chop':true() }"));
    query(_DB_OPEN.args(NAME), "<a/>");
    query(_DB_CREATE.args(NAME, " '<a> </a>'", "a.xml", " map { 'chop':false() }"));
    query(_DB_OPEN.args(NAME), "<a> </a>");

    // specify unknown or invalid options
    error(_DB_CREATE.args(NAME, "()", "()", " map { 'xyz':'abc' }"), BASX_OPTIONS_X);
    error(_DB_CREATE.args(NAME, "()", "()", " map { 'maxlen':-1 }"), BASX_VALUE_X_X);
    error(_DB_CREATE.args(NAME, "()", "()", " map { 'maxlen':'a' }"), BASX_VALUE_X_X);
    error(_DB_CREATE.args(NAME, "()", "()", " map { 'textindex':'nope' }"), BASX_VALUE_X_X);
  }