@Test
public void addWithOptions() {
  query(COUNT.args(COLLECTION.args(NAME)), "1");
  
  // Adding file with options
  query(_DB_ADD.args(NAME, FILE, " map { 'option1': 'value1' }"));
  query(COUNT.args(COLLECTION.args(NAME)), "2");

  // Adding XML content directly with a specified path and options
  query(_DB_ADD.args(NAME, "\"<root/>\"", "t1.xml", " map { 'option2': 'value2' }"));
  query(COUNT.args(COLLECTION.args(NAME + "/t1.xml") + "/root"), "1");

  // Adding document with constructor and options
  query(_DB_ADD.args(NAME, " document { <root/> }", "t2.xml", " map { 'option3': true }"));
  query(COUNT.args(COLLECTION.args(NAME + "/t2.xml") + "/root"), "1");

  // Adding content under a specific path with options
  query(_DB_ADD.args(NAME, " <root/>", "test/t3.xml", " map { 'createIntermediateDirs': true }"));
  query(COUNT.args(COLLECTION.args(NAME + "/test/t3.xml") + "/root"), "1");

  // Adding a file under a specific directory with options
  query(_DB_ADD.args(NAME, FILE, "in/", " map { 'option4': 'value4' }"));
  query(COUNT.args(COLLECTION.args(NAME + "/in/input.xml") + "/html"), "1");

  // Adding a folder with options
  query(_DB_ADD.args(NAME, FLDR, "test/dir", " map { 'recursive': true }"));
  query(COUNT.args(COLLECTION.args(NAME + "/test/dir")), NFLDR);

  // Iterating over files and adding them with options
  query("for $f in " + _FILE_LIST.args(FLDR, "true()", "*.xml") +
        " return " + _DB_ADD.args(NAME, " '" + FLDR + "' || $f", "dir", " map { 'option5': 'value5' }"));
  query(COUNT.args(COLLECTION.args(NAME + "/dir")), NFLDR);

  // Adding multiple documents with dynamic names and options
  query("for $i in 1 to 3 return " +
        _DB_ADD.args(NAME, "\"<root/>\"", "\"doc\" || $i", " map { 'option6': 'value6' }"));
  query(COUNT.args(" for $i in 1 to 3 return " +
        COLLECTION.args('"' + NAME + "/doc\" || $i")), 3);
}