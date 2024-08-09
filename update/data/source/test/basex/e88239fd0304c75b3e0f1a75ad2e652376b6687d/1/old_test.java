@Test
  public void create() {
    // non-existing DB name
    final String dbname = NAME + "DBCreate";

    // create DB without initial content
    query(_DB_CREATE.args(dbname));
    query(_DB_EXISTS.args(dbname), true);

    // create DB w/ initial content
    query(_DB_CREATE.args(dbname, "<dummy/>", "t1.xml"));
    query(_DB_OPEN.args(dbname) + "/root()", "<dummy/>");

    // create DB w/ initial content via document constructor
    query(_DB_CREATE.args(dbname, " document { <dummy/> }", "t2.xml"));
    query(_DB_OPEN.args(dbname) + "/root()", "<dummy/>");

    // create DB w/ initial content given as string
    query(_DB_CREATE.args(dbname, "\"<dummy/>\"", "t1.xml"));
    query(_DB_OPEN.args(dbname) + "/root()", "<dummy/>");

    // create DB w/ initial content multiple times
    query(_DB_CREATE.args(dbname, "<dummy/>", "t1.xml"));
    query(_DB_CREATE.args(dbname, "<dummy/>", "t1.xml"));
    query(_DB_OPEN.args(dbname) + "/root()", "<dummy/>");

    // try to create DB twice during same query
    error(_DB_CREATE.args(dbname) + "," + _DB_CREATE.args(dbname), Err.BXDB_CREATE);

    // create DB from file
    query(_DB_CREATE.args(dbname, FILE, "in/"));
    query(COUNT.args(COLLECTION.args(dbname + "/in/input.xml") + "/html"), "1");

    // create DB from folder
    query(_DB_CREATE.args(dbname, FLDR, "test/dir"));
    query(COUNT.args(COLLECTION.args(dbname + "/test/dir")), NFLDR);

    // create DB w/ more than one input
    query(_DB_CREATE.args(dbname, "(<a/>,<b/>)", "('1.xml','2.xml')"));
    query(_DB_CREATE.args(dbname, "(<a/>,'" + FILE + "')", "('1.xml','2.xml')"));

    error(_DB_CREATE.args(dbname, "()", "1.xml"), Err.BXDB_CREATEARGS);
    error(_DB_CREATE.args(dbname, "(<a/>,<b/>)", "1.xml"), Err.BXDB_CREATEARGS);

    // create and drop more than one database
    query("for $i in 1 to 5 return " + _DB_CREATE.args(" '" + dbname + "' || $i"));
    query("for $i in 1 to 5 return " + _DB_DROP.args(" '" + dbname + "' || $i"));

    error(_DB_CREATE.args(dbname, ""), Err.WHICHRES);

    // create DB with initial EMPTY content
    error(_DB_CREATE.args(""), Err.BXDB_NAME);

    // try to access non-existing DB (create is supposed to be called last)
    query(_DB_DROP.args(dbname));
    error(_DB_CREATE.args(dbname) + "," + _DB_DROP.args(dbname), Err.BXDB_OPEN);

    // run update on existing DB then drop it and create a new one
    query(_DB_CREATE.args(dbname, "<a/>", "a.xml"));
    query("insert node <dummy/> into " + _DB_OPEN.args(dbname));
    query(_DB_CREATE.args(dbname, "<dummy/>", "t1.xml") +
        ", insert node <dummy/> into " + _DB_OPEN.args(dbname) + "," +
        _DB_DROP.args(dbname));
    query(_DB_OPEN.args(dbname) + "/root()", "<dummy/>");

    // eventually drop database
    query(_DB_DROP.args(dbname));
  }