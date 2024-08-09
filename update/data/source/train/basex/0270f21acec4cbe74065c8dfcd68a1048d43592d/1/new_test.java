@Test
  public void finished() {
    final String id = query(_ASYNC_EVAL.args("\"(1 to 100000000)[.=0]\""));
    query(_ASYNC_FINISHED.args(id), "false");
    query(_ASYNC_STOP.args(id));
    error(_ASYNC_FINISHED.args(id), ASYNC_UNKNOWN_X);
  }