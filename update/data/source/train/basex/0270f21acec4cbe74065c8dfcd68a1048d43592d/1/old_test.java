@Test
  public void isRunning() {
    final String id = query(_ASYNC_EVAL.args("\"(1 to 1000000)[.=0]\""));
    query(_ASYNC_IS_RUNNING.args(id), "true");
  }