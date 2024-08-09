@Test
  public void schedule() {
    query(_JOBS_SCHEDULE.args("1"));
    query(_JOBS_SCHEDULE.args(".", " map { '': '1' }"));
    query(_JOBS_SCHEDULE.args(".", " map { '': <a/> }"));
    query(_JOBS_SCHEDULE.args("declare variable $a external;$a", " map { 'a': <a/> }"));
    query(_JOBS_SCHEDULE.args("\"static-base-uri()\"", " map { 'base-uri': 'abc.xq' }"));
    // some errors will only be detected at runtime
    query(_JOBS_SCHEDULE.args("\"db:open('db')\""));
    query(_JOBS_SCHEDULE.args("1+<a/>"));

    // database creation
    error(_DB_OPEN.args("db"), BXDB_OPEN_X);
    query(_PROF_VOID.args(_JOBS_SCHEDULE.args("\"db:open('db')\"")) + ',' + _DB_CREATE.args("db"));
    query(_JOBS_SCHEDULE.args("\"db:drop('db')\"") + ',' + _PROF_VOID.args(_DB_OPEN.args("db")));
    query(_JOBS_SCHEDULE.args("delete node <a/>"));

    // errors
    error(_JOBS_SCHEDULE.args("1+"), CALCEXPR);
    error(_JOBS_SCHEDULE.args("1, delete node <a/>"), UPALL);
  }