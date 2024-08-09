@Test
  public void eval1() {
    query(_JOBS_EVAL.args("1"));
    query(_JOBS_EVAL.args(".", " map { '': '1' }"));
    query(_JOBS_EVAL.args(".", " map { '': <a/> }"));
    query(_JOBS_EVAL.args("declare variable $a external;$a", " map { 'a': <a/> }"));
    query(_JOBS_EVAL.args("\"static-base-uri()\"", " map { 'base-uri': 'abc.xq' }"));
    query(_JOBS_EVAL.args("1", "()", " map{ 'id':'123' }"));
  }