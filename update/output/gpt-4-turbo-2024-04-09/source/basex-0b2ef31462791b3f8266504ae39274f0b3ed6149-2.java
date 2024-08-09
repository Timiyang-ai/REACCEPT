@Test
public void add() {
  query(COUNT.args(COLLECTION.args(NAME)), "1");
  query(_DB_ADD.args(NAME, FILE, " map { }"));
  query(COUNT.args(COLLECTION.args(NAME)), "2");

  query(_DB_ADD.args(NAME, "\"<root/>\"", "t1.xml", " map { }"));
  query(COUNT.args(COLLECTION.args(NAME + "/t1.xml") + "/root"), "1");

  query(_DB_ADD.args(NAME, " document { <root/> }", "t2.xml", " map { }"));
  query(COUNT.args(COLLECTION.args(NAME + "/t2.xml") + "/root"), "1");

  query(_DB_ADD.args(NAME, " <root/>", "test/t3.xml", " map { }"));
  query(COUNT.args(COLLECTION.args(NAME + "/test/t3.xml") + "/root"), "1");

  query(_DB_ADD.args(NAME, FILE, "in/", " map { }"));
  query(COUNT.args(COLLECTION.args(NAME + "/in/input.xml") + "/html"), "1");

  query(_DB_ADD.args(NAME, FILE, "test/t4.xml", " map { }"));
  query(COUNT.args(COLLECTION.args(NAME + "/test/t4.xml") + "/html"), "1");

  query(_DB_ADD.args(NAME, FLDR, "test/dir", " map { }"));
  query(COUNT.args(COLLECTION.args(NAME + "/test/dir")), NFLDR);

  query("for $f in " + _FILE_LIST.args(FLDR, "true()", "*.xml") +
      " return " + _DB_ADD.args(NAME, " '" + FLDR + "' || $f", "dir", " map { }"));
  query(COUNT.args(COLLECTION.args(NAME + "/dir")), NFLDR);

  query("for $i in 1 to 3 return " +
      _DB_ADD.args(NAME, "\"<root/>\"", "\"doc\" || $i", " map { }"));
  query(COUNT.args(" for $i in 1 to 3 return " +
      COLLECTION.args('"' + NAME + "/doc\" || $i")), 3);
}