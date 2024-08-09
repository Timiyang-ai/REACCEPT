@Test
  public void eval() {
    query(_JOBS_EVAL.args("1"));
    query(_JOBS_EVAL.args(".", " map { '': '1' }"));
    query(_JOBS_EVAL.args(".", " map { '': <a/> }"));
    query(_JOBS_EVAL.args("declare variable $a external;$a", " map { 'a': <a/> }"));
    query(_JOBS_EVAL.args("\"static-base-uri()\"", " map { 'base-uri': 'abc.xq' }"));
    query(_JOBS_EVAL.args("1", "()", " map{ 'id':'123' }"));

    // database creation
    error(_DB_OPEN.args("db"), BXDB_OPEN_X);
    query(_PROF_VOID.args(_JOBS_EVAL.args("\"db:open('db')\"")) + ',' + _DB_CREATE.args("db"));
    query(_JOBS_EVAL.args("\"db:drop('db')\"") + ',' + _PROF_VOID.args(_DB_OPEN.args("db")));
    query(_JOBS_EVAL.args("delete node <a/>"));

    // errors (will not be raised before runtime)
    query(_JOBS_EVAL.args("\"db:open('db')\""));
    query(_JOBS_EVAL.args("1+"));
    query(_JOBS_EVAL.args("1, delete node <a/>"));

    // errors
    error(_JOBS_EVAL.args("1", "()", " map{ 'start':'12345' }"), DATEFORMAT_X_X_X);
    error(_JOBS_EVAL.args("1", "()", " map{ 'interval':'12345' }"), DATEFORMAT_X_X_X);
    error(_JOBS_EVAL.args("1", "()", " map{ 'interval':'-PT1S' }"), JOBS_RANGE_X);
    error(_JOBS_EVAL.args("1", "()", " map{ 'id':'job123' }"), JOBS_ID_INVALID_X);
    error(_JOBS_EVAL.args("1", "()", " map{ 'id':'job123' }"), JOBS_ID_INVALID_X);
    error("(1,2)!" + _JOBS_EVAL.args(SLOW_QUERY, "()", " map{ 'id':'abc','cache':true() }"),
        JOBS_ID_EXISTS_X);
  }