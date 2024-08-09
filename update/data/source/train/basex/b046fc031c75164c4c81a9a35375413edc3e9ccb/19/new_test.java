@Test
  public void contains() {
    // check index results
    query(_FT_CONTAINS.args("Assignments", "assignments"), true);
    query(_FT_CONTAINS.args("Exercise 1", "('exercise','1')"), true);
    query(_FT_CONTAINS.args("Exercise 1", "<x>1</x>"), true);
    query(_FT_CONTAINS.args("Exercise 1", "1"), true);
    query(_FT_CONTAINS.args("Exercise 1", "X"), false);
    query(_FT_CONTAINS.args("('A','B')", "('C','B')"), true);

    // check match options
    query(_FT_CONTAINS.args("Assignments", "Azzignments", " map { 'fuzzy':'' }"), true);
    query(_FT_CONTAINS.args("Assignments", "Azzignments", " map { 'fuzzy':'no' }"), false);
    query(_FT_CONTAINS.args("Assignments", "assignment", " map { 'stemming':true() }"), true);
    query(_FT_CONTAINS.args("Assignment", "assignments", " map { 'stemming':true() }"), true);
    query(_FT_CONTAINS.args("A", "a", " map { 'case':'upper' }"), true);
    query(_FT_CONTAINS.args("a", "A", " map { 'case':'lower' }"), true);
    query(_FT_CONTAINS.args("A", "a", " map { 'case':'insensitive' }"), true);
    query(_FT_CONTAINS.args("A", "a", " map { 'case':'sensitive' }"), false);
    // check search modes
    query(_FT_CONTAINS.args("Exercise 1", "1 Exercise", " map { 'mode':'phrase' }"), false);
    query(_FT_CONTAINS.args("Exercise 1", "1 Exercise", " map { 'mode':'all' }"), false);
    query(_FT_CONTAINS.args("Exercise 1", "1 Exercise", " map { 'mode':'any' }"), false);
    query(_FT_CONTAINS.args("Exercise 1", "1 Exercise", " map { 'mode':'any word' }"), true);
    query(_FT_CONTAINS.args("Exercise 1", "1 Exercise", " map { 'mode':'all words' }"), true);

    query(_FT_CONTAINS.args("databases and xml", "databases xml", " map { 'mode':'all words'," +
        "'distance':map {'min':0,'max':1} }"), true);
    query(_FT_CONTAINS.args("databases and xml", "databases xml", " map { 'mode':'all words'," +
        "'distance':map {'max':0} }"), false);
    query(_FT_CONTAINS.args("databases and xml", "databases xml", " map { 'mode':'all words'," +
        "'window':map {'size':3} }"), true);

    // check buggy options
    error(_FT_CONTAINS.args("x", "x", " map { 'x':'y' }"), Err.INVALIDOPT_X);
    error(_FT_CONTAINS.args("x", "x", " map { 'mode':'' }"), Err.INVALIDOPT_X);
    error(_FT_CONTAINS.args("x", "x", " 1"), Err.ELMMAP_X_X_X);
  }