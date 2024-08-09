@Test
  public void finished() {
    final String id = wait(query(_JOBS_SCHEDULE.args(VERY_SLOW_QUERY)));
    try {
      query(_JOBS_FINISHED.args(id), "false");
    } finally {
      query(_JOBS_STOP.args(id));
    }
    while(query(_JOBS_FINISHED.args(id)).equals("false")) Performance.sleep(1);
    query(_JOBS_FINISHED.args("12345"), "true");
  }